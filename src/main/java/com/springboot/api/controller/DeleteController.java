package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

//DELETE API는 웹 애플리케이션 서버를 거쳐 데이터베이스 등의 저장소에 있는 리소스를 삭제할 때 사용합니다.
//서버에서는 클라이언트로부터 리소스를 식별할 수 있는 값을 받아 데이터베이스나 캐시에 있는 리소스를 조회하고 삭제하는 역할을 수행합니다.
//이때 컨트롤러를 통해 값을 받는 단계에서는 간단한 값을 받기 때문에 GET 메서드와 같이 URI에 값을 넣어 요청을 받는 형식으로 구현됩니다.

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

//    @PathVariable을 이용하면 아래와 같이 URI에 포함된 값을 받아 로직을 처리할 수 있습니다.
//    http://localhost:8080/api/v1/delete-api/{String 값}
    @DeleteMapping(value = "/{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        //현재는 데이터베이스 삭제로직 없음, 로그로 대체
        System.out.println(variable + " uri 삭제 완료!!!");
        return variable;
    }

//    @RequestParam을 활용한 DELETE 메서드 구현
//    http://localhost:8080/api/v1/delete-api/request1?email=hotgkswjddn@naver.com
    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String email) {
        return "e-mail : " + email;
    }
}
