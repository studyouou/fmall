package org.og.fmall.fmalluser.mapper;

import org.apache.ibatis.annotations.Param;
import org.og.fmall.commonapi.tkmapper.TkMapper;
import org.og.fmall.fmalluser.model.Member;
import org.og.fmall.user.api.dto.MemberDto;

/**
 * @author:ougen
 * @date:2019/9/309:19
 */
public interface MemberMapper extends TkMapper<Member>{

    Long hasRegiestTel(@Param("tel")Long tel);

    String hasRegitstEmail(@Param("email")String email);

    Member queryMemerByTel(@Param("tel") Long tel);

    Member queryPassById(@Param("id")Long id);

    Member queryMemberByEmail(@Param("email") String email);

    int updatePassword(@Param("id") Long id,@Param("newPassword") String newPassword);
}
