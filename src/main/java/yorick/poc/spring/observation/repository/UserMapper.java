package yorick.poc.spring.observation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    public record UserPO(Long id, String name, int age){}

    @Select("select * from users where name = #{name}")
    UserPO selectUserByName(@Param("name") String name);
}
