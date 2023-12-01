package com.campingmoa.dev.api;

import com.campingmoa.dev.dto.MemberForm;
import com.campingmoa.dev.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

/*    @PostMapping("/auth/join")
    public String saveMember(MemberForm request){
        Long id = memberService.join(request.getEmail(), request.getPassword(), request.getNickname(),"010");
        return "redirect:/";
    }*/


    @PostMapping("/auth/join")
    @ResponseBody
    public CreateMemberResponse saveMember(@RequestBody MemberForm request) {
        Long id = memberService.join(request.getEmail(), request.getPassword(), request.getNickname(), "010");
        return new CreateMemberResponse(id);
    }


    @Data
    private class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
