/**
 * 
 */
package com.jabava.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.manage.EhrPersonMapper;
import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.dao.manage.EhrUserOrganizationMapper;
import com.jabava.pojo.manage.EhrCompany;
import com.jabava.pojo.manage.EhrFunctionPoint;
import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrPersonField;
import com.jabava.pojo.manage.EhrPersonFieldFixvalue;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.manage.EhrUserBusinessPower;
import com.jabava.pojo.manage.EhrUserOrganization;
import com.jabava.pojo.manage.EhrUserPersonPowerValue;
import com.jabava.service.dclient.CenterUserClientService;
import com.jabava.service.system.EhrUserInfoService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.service.system.IEhrRoleService;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.Tools;

/**
 * @author pengyr
 * 
 */

@Service("userInfoService")
public class EhrUserServiceImpl implements EhrUserInfoService {

	@Resource
	private EhrUserMapper ehrUserInfoMapper;
	@Resource
	private EhrCompanyMapper ehrCompanyMapper;
	@Resource
	private EhrUserMapper userMapper;
	@Resource
	private EhrPersonMapper personMapper;
	@Autowired
	private CenterUserClientService centerUserClientService;
	@Resource
	private IEhrRoleService roleService;
	@Autowired
	private IEhrOrganizationService organizationService;
	@Autowired
	private EhrUserOrganizationMapper userOrganizationMapper;

	@Override
	public List<EhrUser> searchUser(Map<String, Object> map, String search, String order, String according, Page<EhrUser> page) throws Exception {
		map.put("according", according);
		map.put("search", search);
		map.put("order", order);
		map.put("page", page);
		return ehrUserInfoMapper.searchUserPage(map);
	}

	@Override
	public List<EhrUser> searchUser(Map<String, Object> map) {
		return ehrUserInfoMapper.searchUser(map);
	}

	//	@Override
//	public Map<String, Object> addOrUpdateUser(EhrUser user) throws Exception {
//		return this.addOrUpdate(user, 3);
////		Map<String, Object> map = new HashMap<>();
////		int num = 0;
////		if(ehrUserInfoMapper.isSingleUserCode(user) > 0){
////			 map.put("msg", "用户编号已经存在");
////			 map.put("success", false);
////			 return map;
////		}
////		if("".equals(user.getUserId()) || user.getUserId() == null){
////			num = ehrUserInfoMapper.insertSelective(user);
////			String password = Tools.getInitialPassword();
////			user.setPassword(Tools.encryptPassword(password));
////			map.put("logInfo", "用户管理   添加用户姓名："+ user.getUserName());
////			roleService.addCommonRoleUser(user);
////			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
////				//用户中心开通
////				Map<String,Object> rr = centerUserClientService.openUser(user,password);
////				if("false".equals(rr.get("success"))){
////					map.put("msg", rr.get("msg"));
////					map.put("success", false);
////					return map;
////				}
////			}
////			
////			if(num > 0){
////				int id = resetPassword(user,password, 1);
////		    	if(id == 3){
////		    		map.put("msg", "重置密码成功,但是密码无法通过邮件发出");
////		    		map.put("success", true);
////		    		return map;
////		    	}
////			}
////		}else{
////			num = ehrUserInfoMapper.updateByPrimaryKeySelective(user);
////			map.put("logInfo", "用户管理   修改用户姓名："+ user.getUserName());
////		}
////		if(num > 0){
////			map.put("msg", "操作成功！");
////			map.put("success", true);
////			return map;
////		}else{
////			map.put("msg", "操作失败！");
////    		map.put("success", false);
////    		return map;
////		}
//	}
//	
//	@Override
//	public Map<String, Object> addOrUpdateHR(EhrUser user) throws Exception {
//		return this.addOrUpdate(user,4);
//	}
	@Override
	public Map<String, Object> addOrUpdate(EhrUser user) throws Exception {
		if(StringUtils.isEmpty(user.getUserCode())){
			throw new JabavaServiceException("用户编号为空");
		}
		if(ehrUserInfoMapper.isSingleUserCode(user) > 0){
			 throw new JabavaServiceException("用户编号已存在");
		}

		if(StringUtils.isEmpty(user.getMobile())){
			throw new JabavaServiceException("手机号为空");
		}
		EhrUser existOfMobile = userMapper.searchEUserByUserMobile(user.getMobile());
		if(existOfMobile != null){
			if(user.getUserId() == null || !existOfMobile.getUserId().equals(user.getUserId())){
				throw new JabavaServiceException("手机号已存在");
			}
		}

		if(!StringUtils.isEmpty(user.getMailAddress())){
			EhrUser existOfEmail = userMapper.searchEUserByUserEmail(user.getMailAddress());
			if(existOfEmail != null){
				if(user.getUserId() == null || !existOfEmail.getUserId().equals(user.getUserId())){
					throw new JabavaServiceException("邮箱已存在");
				}
			}
		}

//		EhrPerson person = new EhrPerson();
//		EhrUser ehrUser  = new EhrUser();
//		ehrUser.setUserCode(user.getUserCode());
//		ehrUser.setCompanyId(user.getCompanyId());
		if(user.getUserId() == null){
			//String password = user.getPassword();
			String password = Tools.getInitialPassword();
			user.setPassword(Tools.encryptPassword(password));
			ehrUserInfoMapper.insertSelective(user);
			if(user.getUserType() == 2){
				roleService.addAdminRoleUser(user);
			}else if(user.getUserType() == 4){
				roleService.addHRRoleUser(user);
			}else{
				//roleService.addCommonRoleUser(user);
			}
			
//			person.setLastModifyDate(user.getLastModifyDate());
//			person.setLastModifyUserId(user.getLastModifyUserId());
//			person.setLastModifyUserName(user.getLastModifyUserName());
//			person.setCreateDate(user.getCreateDate());
//			person.setCreateUserId(user.getCreateUserId());
//			person.setCreateUserName(user.getCreateUserName());
//			person.setEmployeeName(user.getUserName());
//			person.setEmail(user.getMailAddress());
//			person.setMobile(user.getMobile());
//			if(user.getSex() == 1){//男
//				person.setSex((byte) 0);
//			}else if(user.getSex() == 2){//女
//				person.setSex((byte) 1);
//			}
//			person.setCompanyId(user.getCompanyId());
//			personMapper.insertSelective(person);//添加员工
//			//ehrUserInfoMapper.insertRoleUser(user.getUserId());
//			ehrUserInfoMapper.insertUserPerson(user.getUserId(), person.getPersonId());
			
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				//用户中心开通
				Map<String,Object> rr = centerUserClientService.openUser(user,password);
				if("false".equals(rr.get("success").toString())){
					return rr;
				}
			}
			
			int id = resetPassword(user,password, 1);
	    	if(id == 3){
	    		return MessageUtil.errorMessage("密码无法通过邮件发出");
	    	}
		}else{
			ehrUserInfoMapper.updateByPrimaryKeySelective(user);
			
//			Long userId = ehrUserInfoMapper.searchPersonId(user.getUserId());
//			person.setPersonId(userId);
//			person.setLastModifyDate(user.getLastModifyDate());
//			person.setLastModifyUserId(user.getLastModifyUserId());
//			person.setLastModifyUserName(user.getLastModifyUserName());
//			person.setEmployeeName(user.getUserName());
//			person.setEmail(user.getMailAddress());
//			person.setMobile(user.getMobile());
//			if(user.getSex() == 1){//男
//				person.setSex((byte) 0);
//			}else if(user.getSex() == 2){//女
//				person.setSex((byte) 1);
//			}
//			personMapper.updateByPrimaryKeySelective(person);//修改员工
			
			if("1".equals(JabavaPropertyCofigurer.getSsoSwitch())){
				//更新用户中心用户信息
				Map<String,Object> rr = centerUserClientService.updateUser(user);
				if("false".equals(rr.get("success").toString())){
					return rr;
				}
			}
		}

		return MessageUtil.successMessage("操作成功");
	}
	
	public Map<String, Object> pwdReset(EhrUser user, int entry) throws Exception{
		int num = resetPassword(user,Tools.getInitialPassword(), 0);
		if(num == 0){
			return MessageUtil.errorMessage("重置密码没有成功！");
    	}else if(num == 2){
            return MessageUtil.successMessage("重置密码成功,密码已经通过邮件发出！");
    	}else{
			return MessageUtil.errorMessage("重置密码成功,但是密码无法通过邮件发出！");
    	}
	}
	
	public int resetPassword(EhrUser user, String password, int entry) throws Exception{
		//String password = ;
		user.setPassword(Tools.encryptPassword(password));
		int returnValue = 0;
		try{
			String subject;
			String content;
			returnValue = 1;
			if (entry == 1) {	//不需要reset，直接发邮件
				subject = "您在Jabava的帐号开通啦~";
				content = user.getUserName()
						+ ",您好，<br><br>  "
						+ "您在Jabava的帐号开通了，用户名："
						+ user.getUserCode()
						+ "，密码："
						+ password
						+ "。<br>"
						+ "请尽快登录系统更改密码。<br><br>"
						+ "感谢您使用Jabava系统! <br><br>"
						+ "Jabava 系统组<br><br>"
						+ "******该邮件由系统自动发出,请勿直接回复。若有任何问题，请联络系统管理员或HR！******<br>";
			}else{
				ehrUserInfoMapper.resetPassword(user);
				
				subject = "密码设置通知";
				content = user.getUserName()+", 您好,<br><br>"
						+ "您在Jabava系统中的密码被管理员重新设置了。<br>"
						+ "用户名： "+user.getUserCode()+"<br>"
						+ "新的密码： "+password+"<br>"
						+ "建议您尽快登陆系统修改此密码。<br><br><br>"
						+ "祝您工作愉快!<br><br>"
						+ "Jabava系统邮件组<br><br>"
						+ "******本邮件由系统自动发送，请勿直接回复。******";
			}
			Tools.mailSend(user.getMailAddress(), subject, content, null);
			returnValue = 2;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			returnValue = 3;
		}
		return returnValue;	
	}

	@Override
	public EhrUser getUserById(Long userId) throws Exception {
		EhrUser user =  ehrUserInfoMapper.selectByPrimaryKey(userId);
		user.setCompany(ehrCompanyMapper.selectByPrimaryKey(user.getCompanyId()));
		return user;
	}

	@Override
	public Map<String, Object> deleteUser(Long userId,Integer isValid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if(ehrUserInfoMapper.delById(userId,isValid) > 0){
			map.put("success", true);
			map.put("msg", MessageUtil.DEL_SUCCESS);
		}else{
			map.put("success", false);
			map.put("msg", MessageUtil.DEL_ERROR);
		}
		return map;
	}

	//----------------------------companyTree开始-------------------------
	@Override
	public String companyTree(Long companyId) throws Exception {
		Map<EhrCompany, List<EhrCompany>> map = getCompanyTreeMap();
		Iterator<EhrCompany> it=map.keySet().iterator();
		EhrCompany root=null;
		while(it.hasNext())
		{
			EhrCompany mdl=it.next();
			if(mdl.getParentId().longValue()==0)
			{
				root=mdl;
				break;
			}
		}
		StringBuffer sb=new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>\r\n");
		sb.append("<tree id=\"0\">");
		this.printCompanyTree(sb, root, map, companyId);
		sb.append("</tree>"); 
		return sb.toString();
	}

	public Map<EhrCompany, List<EhrCompany>> getCompanyTreeMap()
			throws Exception {
		Map<EhrCompany, List<EhrCompany>> map = new HashMap<EhrCompany, List<EhrCompany>>();
    	List<EhrCompany> tree = ehrCompanyMapper.getCompanyList();
    	EhrCompany root = ehrCompanyMapper.getZeroCompany();
    	Stack<EhrCompany> stack = new Stack<EhrCompany>();
    	stack.push(root);
    	while(!stack.isEmpty())
    	{
    		EhrCompany mdl = stack.pop();
        	List<EhrCompany> children = this.getChildrenByParent(mdl.getCompanyId(), tree);
        	map.put(mdl, children);
            for(int i=0;i<children.size();i++)
            {
            	stack.push(children.get(i));
            }
    	}
    	return map;
	}
	
	public List<EhrCompany> getChildrenByParent(long parentId, List<EhrCompany> tree)
    {
    	List<EhrCompany> v = new ArrayList<EhrCompany>();
    	for(int i = 0; i < tree.size(); i++)
    	{
    		EhrCompany mdl = (EhrCompany) tree.get(i);
    		if(mdl.getParentId().intValue()==parentId)
    		{
    			v.add(mdl);
    			tree.remove(i);
    			i--;
    		}
    	}
    	return v;
    }
	
	public void printCompanyTree(StringBuffer sb,EhrCompany mdl, Map<EhrCompany,List<EhrCompany>> map,long companyId)
	{
		sb.append("<item text=\""+mdl.getCompanyName()+"\" id=\""+mdl.getCompanyId()+"\" "+(mdl.getCompanyId().longValue()==companyId?"open=\"1\"":""));
		List<EhrCompany> v = map.get(mdl);
		if(v == null || v.size() == 0){
			sb.append(" im0=\"folderOpen.gif\"");
		}
		sb.append(">\r\n");
		if(v != null && v.size() > 0){
			for(int i = 0; i < v.size(); i++)
			{
				printCompanyTree(sb,v.get(i),map,companyId);
			}
		}
		sb.append("</item>\r\n");
	}
	//----------------------------companyTree结束-------------------------
	
	
	@Override
	public List<EhrPersonField> getPersonFieldList() throws Exception {
		List<EhrPersonField> list = userMapper.getPersonFieldList();
		for (EhrPersonField ehrPersonField : list) {
			if(ehrPersonField.getFieldType().intValue()==4){
				List<EhrPersonFieldFixvalue> listValue = userMapper.getPersonFieldfixvalue(ehrPersonField.getFieldId());
				ehrPersonField.setFieldValueList(listValue);
			}
		}
		return list;
	}

	@Override
	public List<EhrUserBusinessPower> getUserPowerList(Long userId)
			throws Exception {
		List<EhrUserBusinessPower> list = userMapper.getUserPowerList(userId);
		for (EhrUserBusinessPower ehrUserBusinessPower : list) {
			if(ehrUserBusinessPower.getFieldType().intValue()==1){
				EhrPersonField  Field = userMapper.getPersonFieldId(ehrUserBusinessPower.getFieldId());
				 ehrUserBusinessPower.setPersonField(Field);
				 if(ehrUserBusinessPower.getPersonField()!=null && ehrUserBusinessPower.getPersonField().getFieldType().intValue()>=4){
					 List<EhrUserPersonPowerValue>  listValue = userMapper.getPersonPowerValue(ehrUserBusinessPower.getUserPersonPowerId());
					 ehrUserBusinessPower.setPowerValueList(listValue);
				 }
			}
		}
		return list;
	}


	@Override
	public List<EhrFunctionPoint> getFunctionPoint() throws Exception {
		List<EhrFunctionPoint> list = userMapper.getFunctionPoint();
		return list;
	}

	@Override
	public List<String[]> getSelectValueInfo(
			Integer fieldId, EhrUser user ) {
		List<String[]> returnList = new ArrayList<String[]>();
		EhrPersonField ehrPersonField = userMapper.getPersonFieldId(fieldId);
		List<EhrPersonFieldFixvalue> list = null;
		if(ehrPersonField.getFieldType().intValue() == 4){
			list = userMapper.getPersonFieldfixvalue(fieldId);
		}else if(ehrPersonField.getFieldType().intValue() == 5){
			String strSql = ehrPersonField.getRelateSql().replace("field_key", "fieldKey").replace("field_value", "fieldValue");
			if(ehrPersonField.getFieldId().intValue() < 100){
				strSql +=" and company_id = " + user.getCompanyId();
			}
			list = userMapper.getPersonFieldSql(strSql);
		}
		for(int i = 0; i < list.size(); i++){
			EhrPersonFieldFixvalue item=list.get(i);
			String[] strs=new String[3];
			strs[0]=item.getFieldKey();
			strs[1]=item.getFieldValue();
			strs[2]="0";
			returnList.add(strs);
		}
		return returnList;
	}

	@Override
	public void insertBusinessPower(Long userId, String field)
			throws Exception {
		List<Integer> functionPoints = new ArrayList<Integer>();
		functionPoints.add(Integer.parseInt(userId.toString()));
		userMapper.deleteUserPersonPowerValue(userId);
		userMapper.deleteUserBusinessPower(userId);
		String list[] = field.split("/");
		for(int i = 0; i < list.length; i++){
			String f[] = list[i].split("#");
			String code = f[0];
			String fieldType = f[1];
			String fieldId = f[2].equals("-1") ? "" : f[2];
			String operateType = f[3].equals("-1") ? "" : f[3];
			String fieldValue = f[4].equals("-1") ? "" : f[4];
			Integer functionPointCode = new Integer(1);
			if(code != null && !code.equals("")){
				functionPointCode = Integer.parseInt(code);
			}
			if(!dataInList(functionPointCode,functionPoints)){
				functionPoints.add(functionPointCode);
			}
			if(!fieldType.equals("1")){
				EhrUserBusinessPower mdl=new EhrUserBusinessPower();
				mdl.setUserId(new Long(userId));
				mdl.setSeqNo(new Integer(i+1));
				mdl.setFieldType(Byte.valueOf(fieldType));
				mdl.setFunctionPointCode(functionPointCode);
				userMapper.insertBusinessPower(mdl);
			}else{
				if(fieldValue == null || "null".equals(fieldValue)){
					fieldValue = "";
				}
				EhrUserBusinessPower mdl=new EhrUserBusinessPower();
				mdl.setUserId(new Long(userId));
				mdl.setFieldId(new Integer(fieldId));
				mdl.setSeqNo(new Integer(i+1));
				mdl.setFieldType(Byte.valueOf(fieldType));
				mdl.setFunctionPointCode(functionPointCode);
				Byte fType = getPersonFieldId(Integer.parseInt(fieldId)).getFieldType();
				if(new Integer(fType).intValue() >= 4){
					mdl.setFieldValue(fieldValue);
					userMapper.insertBusinessPower(mdl);
					long id = mdl.getUserPersonPowerId();
					String[] fieldValueIds = operateType.split(",");
					String[] values = fieldValue.split(",");
					for(int j = 0; j < fieldValueIds.length; j++){
						EhrUserPersonPowerValue item = new EhrUserPersonPowerValue();
						item.setUserPersonPowerId(id);
						item.setFieldId(mdl.getFieldId());
						item.setFieldKey(fieldValueIds[j]);
						item.setFieldValue(values[j]);
						userMapper.insertPersonPower(item);
					}
				}else{
					mdl.setOperateType(Byte.valueOf(operateType));
					mdl.setFieldValue(fieldValue);
					userMapper.insertBusinessPower(mdl);			
				}
			}
		}
	}
	
	private boolean dataInList(Integer data, List<Integer> v){
		for(int i = 0; i < v.size(); i++){
			if(v.get(i).intValue() == data.intValue())
				return true;
		}
		return false;
	}

	

	@Override
	public int deleteUserPersonPowerValue(Long userId) {
		
		return userMapper.deleteUserPersonPowerValue(userId);
	}

	@Override
	public int deleteUserBusinessPower(Long userId) {
		
		return userMapper.deleteUserBusinessPower(userId);
	}

	@Override
	public EhrPersonField getPersonFieldId(Integer fieldId) throws Exception {
		
		return userMapper.getPersonFieldId(fieldId);
	}

	@Override
	public Map<String, Object> isDeleteUser(Long userId,Integer isDelete) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		if(userMapper.isDeleteUser(userId,isDelete) > 0){
//			Long personId = userMapper.searchPersonId(userId);
//			if(personId != null){
//			personMapper.isDeletePerson(personId,isDelete);
//			}
			map.put("success", true);
			map.put("msg", MessageUtil.DEL_SUCCESS);
		}else{
			map.put("success", false);
			map.put("msg", MessageUtil.DEL_ERROR);
		}
		return map;
	}

	@Override
	public List<EhrUserOrganization> listUserOrganization(EhrUser user) {
		return userOrganizationMapper.listByUserId(user.getUserId());
	}

	@Override
	public List<Map<String, Object>> loadUserOrganizationTree(EhrUser user) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		//获取用户组织并放入Map
		List<EhrUserOrganization> userOrgList = userOrganizationMapper.listByUserId(user.getUserId());
		Map<Long,Object> userOrgMap = new HashMap<Long,Object>();
		for(EhrUserOrganization uo : userOrgList){
			userOrgMap.put(uo.getOrganizationId(), uo);
		}
		
		//按层级加载组织树并遍历
		List<EhrOrganization> orgList = organizationService.loadTree(user.getCompanyId());
		for(EhrOrganization org : orgList){
			Map<String,Object> orgMap = new HashMap<String,Object>();
			orgMap.putAll(new BeanMap(org));
			orgMap.remove("class");
			if(userOrgMap.containsKey(org.getOrganizationId())){
				orgMap.put("selected", true);
			}else{
				orgMap.put("selected", false);
			}
			result.add(orgMap);
		}
		
		return result;
	}

	@Override
	public int saveUserOrganization(Long userId, Long[] orgIds) throws Exception {
		//获取用户组织并放入Map
		List<EhrUserOrganization> userOrgList = userOrganizationMapper.listByUserId(userId);
		Map<Long,Long> userOrgMap = new HashMap<Long,Long>();
		for(EhrUserOrganization uo : userOrgList){
			userOrgMap.put(uo.getOrganizationId(), uo.getId());
		}
		
		for(Long orgId : orgIds){
			if(userOrgMap.containsKey(orgId)){
				//原来有现在有的则保留
				userOrgMap.remove(orgId);
			}else{
				//原来没有现在有的则新增
				EhrUserOrganization uo = new EhrUserOrganization();
				uo.setUserId(userId);
				uo.setOrganizationId(orgId);
				userOrganizationMapper.insertSelective(uo);
			}
		}
		
		//原来有现在没有的则删除
		for(Long id : userOrgMap.values()){
			userOrganizationMapper.deleteByPrimaryKey(id);
		}
		
		return 1;
	}

	@Override
	public int deleteUserOrganization(Long userId) {
		userOrganizationMapper.deleteByUser(userId);
		return 1;
	}

}
