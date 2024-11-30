package demo.test.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author
 * @date 2024-11-29 23:44
 */
public interface TestMapper {

    @Select("select sleep(#{t});")
    Integer sleep(@Param("t") int t);

    @Select("select `id` from t1 where `c`=1;")
    Long query();

    @Select("select `id` from t1 where `c`=1;")
    Long query2();
}
