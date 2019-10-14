//package org.og.fmall.fmalluser;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.og.fmall.commonapi.exception.BaseException;
//import org.og.fmall.commonapi.utils.TelValidateUtil;
//import org.og.fmall.fmalluser.model.Member;
//import org.og.fmall.user.api.dto.MemberRequest;
//import org.og.fmall.user.api.dto.MemberResponse;
//import org.og.fmall.user.api.iservice.IMemberCoreService;
//import org.og.fmall.user.api.iservice.IMemberQueryService;
//
///**
// * @author:ougen
// * @date:2019/10/316:14
// */
//@Slf4j
//public class MemberTest extends FmallUserApplicationTests {
//
//    @Reference
//    private IMemberCoreService iMemberCoreService;
//
//    @Reference
//    private IMemberQueryService iMemberQueryService;
//
//    private String uuid;
//
//    @Test
//    public void testRegistMember(){
//        MemberRequest request = new MemberRequest();
//        request.setPassword("zhangxinyu1314");
//        request.setTel(13981973373L);
//        request.setAge(23);
//        request.checked();
//        try {
//            TelValidateUtil.isMobile(request.getTel());
//        }catch (BaseException e){
//            log.error(e.getMsg());
//            return;
//        }
//        MemberResponse response = iMemberCoreService.registMember(request);
//        Assert.assertEquals(response.getMsg(),0,response.getCode());
//    }
//
//    @Test
//    public void testLogin(){
//        MemberRequest request = new MemberRequest();
//        request.setTel(13981973371L);
//        request.setPassword("ougen123");
//        MemberResponse login = iMemberCoreService.login(request);
//        uuid = login.getUuid();
//        Assert.assertEquals(login.getMsg(),0,login.getCode());
//    }
//
//    @Test
//    public void testUpdatePassword(){
//        MemberRequest request = new MemberRequest();
//        request.setId(2L);
//        request.setUuid(uuid);
//        request.setSalt("mUo4oJrf");
//        request.setPassword("zhangxinyu1314");
//        request.setNewPassword("ougen123");
//        request.setRePassword("ougen123");
//        MemberResponse response = iMemberCoreService.updatePassword(request);
//        Assert.assertEquals(response.getMsg(),0,response.getCode());
//    }
//}
