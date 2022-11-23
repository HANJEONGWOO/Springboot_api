package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 리퀘스트 맵핑은 리퀘스트 uri를 맵핑하는 용도
// Get/Post/Put/Delete 맵핑은 각 REST를 맵핑하는 용도로 사용하면 될 듯
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // 예제 5.26
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // http://localhost:8080/api/v1/get-api/hello

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    스프링 4.3 버전 이후로는 새로 나온 아래의 어노테이션을 사용하기 때문에 @RequestMapping 어노테이션은
//     더 이상 사용되지 않습니다. 이 책에서도 이후 예제에서는 특별히 @RequestMapping을 활용해야 하는 내용이 아니라면,
//    아래의 각 HTTP 메서드에 맞는 어노테이션을 사용할 예정입니다.
//    -GetMapping
//    -PostMapping
//    -PutMapping
//    -DeleteMapping
    @GetMapping("/hello")
    public String getHello() {
        // 예제 5.27
        LOGGER.info("getHello 메소드가 호출되었습니다.");
        return "Hello World";
    }

    @GetMapping("/name")
    public String getName() {
        // 예제 5.27
        LOGGER.info("getName 메소드가 호출되었습니다.");
        return "Han Jeongwoo";
    }

//    첫번째 방법. "PathVariable" 을 활용한 GET 메서드 구현
//    실무 환경에서는 매개변수를 받지 않는 매서드가 거의 쓰이지 않습니다. 웹 통신의 기본 목적은 데이터를 주고받는 것이기 때문에
//    대부분 매개변수를 받는 매서드를 작성하게 됩니다. 매개변수를 받을 때 자주 쓰이는 방법 중 하나는 URL 자체에 값을 담아 요청하는 것입니다.
//    예제 5.4는 URL에 값을 담아 전달되는 요청을 처리하는 방법을 보여줍니다.
//    http://localhost:8080/api/v1/get-api/PathVariable/123/456
    @GetMapping(value="/PathVariable/{variable}/{variable1}")
    public String getPathVariable(@PathVariable String variable, @PathVariable String variable1) {
        // 예제 5.28
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}, {}", variable, variable1);
        return variable + ", " + variable1;
    }

//    !!! 예외 !!!
//    만약 GetMapping 어노테이션에 지정한 변수의 이름과 메서드 매개변수의 이름을 동일하게 맞추기 어렵다면 PathVariable 뒤에 괄호를 열어
//    GetMapping 어노테이션의 변수명을 지정합니다.
    // http://localhost:8080/api/v1/get-api/PathVariable2/123
    @GetMapping(value = "/PathVariable2/{variable}")
    public String getPathVariable1(@PathVariable("variable") String var) {
        return var;
    }




//    두번째 방법. "RequestParam" 을 활용한 GET 메서드 구현
//    GET 요청을 구현할 때 앞에서 살펴본 방법처럼 URL 경로에 값을 담아 요청을 보내는 방법 외에도 쿼리 형식으로 값을 전달할 수도 있습니다.
//        즉, URI에서 '?'를 기준으로, 우측에 '{키}={값}' 형태로 구성된 요청을 전송하는 방법입니다. 애플리케이션에서 이 같은 형식을 처리하려면
//    @RequestParam을 활용하면 되는데, 예제 5.6과 같이 매개변수 부분에 @RequestParam 어노테이션을 명시해 쿼리 값과 매핑하면 됩니다.
    // http://localhost:8080/api/v1/get-api/RequestParam?name=한정우&email=hotgkswjddn@naver.com&organization=삼성전자
    @ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/RequestParam")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return "name:"+name+", email:"+email+", organization:"+organization;
    }

//    만약 쿼리스트링에 어떤 값이 들어올지 모른다면 예제 5.7과 같이 Map 객체를 활용할 수도 있습니다.
//    http://localhost:8080/api/v1/get-api/RequestParam2?key1=value&key2=value2
    @GetMapping(value = "/RequestParam2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

//        entrySet으로 접근하기. Map은 하나의 원소로 Key-Value 묶음을 가지기 때문에 원소란 표현 대신 Entry라고 표현합니다.
        for(Map.Entry<String, String> entry : param.entrySet()) {
            sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
        }

        return sb.toString();
    }




//  DTO 객체를 활용한 GET 메서드 구현
//  DTO 클래스에는 전달하고자 하는 필드 객체를 선언하고 getter/setter 메서드를 구현합니다.
//  DTO 클래스에 선언된 필드는 컨트롤러의 메서드에서 쿼리 파라미터의 키와 매핑됩니다.
//  즉, 쿼리스트링의 키가 정해져 있지만 받아야 할 파라미터가 많을 경우에는 예제 5.9와 같이 DTO 객체를 활용해 코드의 가독성을 높일 수 있습니다.
//  http://localhost:8080/api/v1/get-api/RequestParam3?name=한정우&email=hotgkswjddn@naver.com&organization=삼성전자
    @GetMapping(value = "/RequestParam3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }

}
