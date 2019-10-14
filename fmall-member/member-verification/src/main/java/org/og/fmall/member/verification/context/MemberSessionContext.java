package org.og.fmall.member.verification.context;

import org.og.fmall.user.api.session.MemberSession;

/**
 * @author:ougen
 * @date:2019/10/216:15
 */
public class MemberSessionContext {
    private static final ThreadLocal<MemberSession> MEMBER_HOLDER = new ThreadLocal<>();

    public static MemberSession getMemberSession(){
        return MEMBER_HOLDER.get();
    }

    public static void setMemberSession(MemberSession memberSession){
        MEMBER_HOLDER.set(memberSession);
    }

    public static void deleteMemberSession(){
        MEMBER_HOLDER.remove();
    }
}
