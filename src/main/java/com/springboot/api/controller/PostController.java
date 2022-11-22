package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//POST API 만들기
//POST API는 웹 애플리케이션을 통해 데이터베이스 등의  저장소에 리소스를 저장할 때 사용되는 API 입니다.
//앞에서 살펴본 GET API에서는 URL의 경로나 파라미터에 변수를 넣어 요청을 보냈지만 POST API에서는 저장하고자 하는 리소스나 값을
//HTTP 바디에 담아 서버에 전달합니다. 그래서 URI가 GET API에 비해 간단합니다.
//POST API를 만들기 위해 우선 예제 5.10과 같이 PostController라는 이름의 컨트롤러 클래스를 생성하고,
//RequestMapping 어노테이션을 이용해 공통 URL을 설정합니다.
@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

//    http://localhost:8080//api/v1/post-api/domain
    @PostMapping(value = "/domain")
    public String postExample() {
        return "Hello Post API";
    }

//    http://localhost:8080/api/v1/post-api/member
//{
//    "name" : "hjw",
//        "email" : "hotgkswjddn@naver.com",
//        "organization" : "samsung"
//}
//    RequestBody와 Map을 이용한 POST API 구현
//    @RequestBody라는 어노테이션을 사용했는데, RequestBody는 HTTP의 Body 내용을 해당 어노테이션이 지정된 객체에 매핑하는 역할을 합니다.
//    Map객체는 요청을 통해 어떤 값이 들어오게 될 지 특정하기 어려울 때 주로 사용합니다.
//      요청 메시지에 들어갈 값이 정해져 있다면 다음 예제와 같이 DTO 객체를 매개변수로 삼아 작성할 수 있습니다.
//    이 예제의 DTO객체는 GET에서의 예제에서 사용한 것과 동일하므로 설명은 생략합니다.
    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    //DTO 객체를 활용한 POST API 구현
//    http://localhost:8080/api/v1/post-api/member2
//    {
//        "name" : "hjw",
//            "email" : "hotgkswjddn@naver.com",
//            "organization" : "samsung"
//    }
    @PostMapping(value="/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
