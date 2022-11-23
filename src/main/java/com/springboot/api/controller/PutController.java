package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//PUT API는 웹 애플리케이션 서버를 통해 데이터베이스 같은 저장소에 존재하는 리소스 값을 업데이트
//하는 데 사용합니다. POST API와 비교하면 요청을 받아 실제 데이터베이스에 반영하는 과정(서비스 로직)
//에서 차이가 있지만 컨트롤러 클래스를 구현하는 방법은 POST API와 거의 동일합니다.
//리소스를 서버에 전달하기 위해 HTTP Body를 활용해야 하기 때문입니다.


@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

// http://localhost:8080/api/v1/put-api/member
//    {
//        "name" : "hjw",
//            "email" : "hotgkswjddn@naver.com",
//            "organization" : "samsung"
//    }
    @PutMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map-> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

//    서버에 어떤 값이 들어올지 모르는 경우에는 Map객체를 활용해 값을 받을 수 있습니다.
//    대부분의 경우 API를 개발한 쪽에서 작성한 명세(specification)를 웹 사이트를 통해 클라이언트나 사용자에게
//    올바른 사용법을 안내합니다. 만약 서버에 들어오는 요청에 담겨 있는 값이 정해져 있는 경우에는
//    예제 5.16과 같이 DTO객체를 활용해 구현합니다.
    // http://localhost:8080/api/v1/put-api/member1
    @PutMapping(value = "/member1")
    public String postMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto.toString(); //리턴 값이 String이므로, String 타입으로 값을 전달받게 됨(text/plain)
    }

    // http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto; //리턴 값이 DTO 객체이므로, DTO 타입으로 값을 전달받게 됨(application/json)
    }
    
    
//    ResponseEntity를 활용한 PUT 메서드 구현
//    스프링 프레임워크에는 HttpEntity라는 클래스가 있습니다.
//    HttpEntity는 다음과 같이 헤더(Header)와 바디(Body)로 구성된 HTTP요청과 응답을 구성하는 역할을 수행합니다.
//    RequestEntity와 ResponseEntity는 HttpEntity를 상속받아 구현한 클래스입니다.
//    그 중 ResponseEntity는 서버에 들어온 요청에 대해 응답 데이터를 구성해서 전달할 수 있게 합니다.
//    다음과 같이 ResponseEntity는 HttpEntity로부터 HttpHeaders와 Body를 가지고 자체적으로 HttpStatus를 구현합니다.

//    예제의 3번 줄에서는 메서드의 리턴 타입을 ResponseEntity로 설정하고 4~6번 줄에서 리턴 값을 만듭니다.
//    status에 넣을 수 있는 값은 다양한데, 예제에서 사용한 HttpStatus.ACCEPTED는 응답 코드 202를 가지고 있습니다.(기존 200)
//    즉, 이 메서드를 대상으로 요청을 수행하면 그림 5.22와 같이 응답 코드가 202로 변경됩니다.

//    http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
