package cn.com.pajk.mapper;

import cn.com.pajk.dto.Case;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResultCaseMapper {
    @Insert("insert into webui_case " +
            "(case_name,case_class,case_parameters,case_description,case_type,country_id,business_id,gmt_creste,gmt_modified,is_valid)" +
            "values " +
            "(#{caseName},#{caseClasee})"
            // shenglue
            )
    @Options(useGeneratedKeys = true, keyProperty = "id")
     int insert(Case caseP);
    @Delete("delete from web_ui where id= #{id}")
    int delete(int id);
    @Select("select * from web_ui where case_name=#{case_name}")
    List<Case> getCase(String caseName);
    @Select("select * from web_ui")
    @Results({
            @Result(property = "caseName",column = "case_name"),
            @Result(property = "caseClass",column = "case_class"),
            //省略
    })
    List<Case> listAllCases();


}
