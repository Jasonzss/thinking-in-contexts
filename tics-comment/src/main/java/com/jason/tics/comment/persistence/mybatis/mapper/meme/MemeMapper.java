package com.jason.tics.comment.persistence.mybatis.mapper.meme;

import com.jason.tics.comment.meme.Meme;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Jason
 */
public interface MemeMapper {
    @Select("select * from meme where meme_id = #{memeId}")
    Meme getMeme(int memeId);

    @Select("select * from meme")
    List<Meme> listMemes();

    @Select("select * from meme where meme_id in #{id}")
    List<Meme> listMemes(@Param("id") int[] id);

    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "memeId", keyColumn = "meme_id", before = false, resultType = int.class)
    @Insert("insert into meme (meme_name, meme_image, create_time) values (#{memeName}, #{memeImage}, now())")
    int addMeme(Meme meme);

    @Delete("delete from meme where meme_id = #{memeId}")
    int deleteMeme(@Param("memeId") int memeId);

    @Update("update meme set meme_name = #{memeName} where meme_id = #{memeId}")
    int updateMemeName(@Param("memeId") int memeId,@Param("memeName") String memeName);

    @Update("update meme set meme_image = #{memeImage} where meme_id = #{memeId}")
    int updateMemeImage(@Param("memeId") int memeId,@Param("memeImage") String memeImage);
}
