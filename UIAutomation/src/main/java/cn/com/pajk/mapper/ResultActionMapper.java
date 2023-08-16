package cn.com.pajk.mapper;

import cn.com.pajk.dto.ActionResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResultActionMapper {
    @Insert("insert into ")
    int insert(ActionResult actionResult);

}
