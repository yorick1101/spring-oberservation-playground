package yorick.poc.spring.observation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserWalletMapper {

    public record UserWalletPO(Long userId, double balance){}

    @Select("select * from user_wallet where user_id = #{userId}")
    UserWalletPO selectWalletById(@Param("userId") String userId);
}
