package com.jason.tics.content.resource;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.Comment;
import com.jason.tics.content.domain.dto.CommentDto;
import com.jason.tics.content.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jason
 */
@Controller
@RequestMapping(path = {"/video", "/audio", "essay"})
public class CommentResource {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}/comment")
    public PageResult<Comment> listComments(@PathVariable String id,
                                            @RequestParam(required = false, defaultValue = "like") String sort,
                                            @RequestParam(defaultValue = "false") boolean descend,
                                            @RequestParam(required = false) int page,
                                            @RequestParam(required = false, defaultValue = "15") int size){
        return commentService.listSortedComments(id, sort, descend, page, size);
    }

    @GetMapping("/{id}/comment/{cid}")
    public Comment getComment(@PathVariable long cid){
        return commentService.getComment(cid);
    }

    @PostMapping("/{id}/comment")
    public Comment addComment(@Validated @RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto);
    }

    @DeleteMapping("/{id}/comment/{cid}")
    public void deleteComment(@PathVariable long cid){
        commentService.deleteComment(cid);
    }

    @PutMapping("/{id}/comment/{cid}")
    public String updateComment(@PathVariable long cid, @RequestParam String content){
        return commentService.updateCommentContent(cid, content);
    }

    @DeleteMapping("/{id}/comment/{cid}/attachment")
    public void deleteCommentAttachment(@RequestParam String[] attachment,@PathVariable long cid){
        commentService.deleteCommentAttachments(cid, attachment);
    }

    @PostMapping("/{id}/comment/{cid}/attachment")
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
