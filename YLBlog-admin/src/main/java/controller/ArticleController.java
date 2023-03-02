package controller;

import com.yelan.domain.dto.AddArticleDto;
import com.yelan.domain.dto.ArticleDto;
import com.yelan.domain.entity.Article;
import com.yelan.domain.result.ResponseResult;
import com.yelan.domain.vo.ArticleVo;
import com.yelan.domain.vo.PageVo;
import com.yelan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ArticleController
 * @Description:
 * @Author: ChenKo
 * @Date: 2023/3/2
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){

        return articleService.add(article);
    }
    @GetMapping("/list")
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize)
    {

        return articleService.selectArticlePage(article,pageNum,pageSize);
    }
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){

        return articleService.getInfo(id);
    }
    @PutMapping
    public ResponseResult edit(@RequestBody ArticleDto article){

        return articleService.edit(article);
    }
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }

}
