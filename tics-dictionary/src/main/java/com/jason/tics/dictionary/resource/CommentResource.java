package com.jason.tics.dictionary.resource;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.Comment;
import com.jason.tics.dictionary.constraints.IsWord;
import com.jason.tics.dictionary.domain.dto.CommentDto;
import com.jason.tics.dictionary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jason
 */
@RestController
@RequestMapping("/dictionary")
public class CommentResource {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{word}/comment")
    public PageResult<Comment> listComments(@PathVariable @IsWord String word,
                                            @RequestParam(required = false, defaultValue = "like") String sort,
                                            @RequestParam(defaultValue = "false") boolean descend,
                                            @RequestParam(required = false) int page,
                                            @RequestParam(required = false, defaultValue = "15") int size){
        return commentService.listSortedComments(word, sort, descend, page, size);
    }

    @GetMapping("/{word}/comment/{cid}")
    public Comment getComment(@PathVariable @IsWord String word, @PathVariable long cid){
        return commentService.getComment(cid);
    }

    @PostMapping("/{word}/comment")
    public Comment addComment(@PathVariable @IsWord String word, @Validated @RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto);
    }

    @DeleteMapping("/{word}/comment/{cid}")
    public void deleteComment(@PathVariable long cid){
        commentService.deleteComment(cid);
    }

    @PutMapping("/{word}/comment/{cid}")
    public String updateComment(@PathVariable long cid, @RequestParam String content){
        return commentService.updateCommentContent(cid, content);
    }

    @DeleteMapping("/{word}/comment/{cid}/attachment")
    public void deleteCommentAttachment(@RequestParam String[] attachment,@PathVariable long cid){
        commentService.deleteCommentAttachments(cid, attachment);
    }

    @PostMapping("/{word}/comment/{cid}/attachment")
    public String[] addCommentAttachment(@PathVariable long cid,@RequestParam String[] attachments){
        return commentService.addCommentAttachments(cid, attachments);
    }

    @DeleteMapping("/{id}/comment/{cid}/like")
    public void deleteCommentLike(@PathVariable long cid){
        long uid = 1;
        commentService.disLikeComment(uid, cid);
    }

    @PostMapping("/{id}/comment/{cid}/like")
    public void addCommentLike(@PathVariable long cid){
        long uid = 1;
        commentService.likeComment(uid, cid);
    }
}
