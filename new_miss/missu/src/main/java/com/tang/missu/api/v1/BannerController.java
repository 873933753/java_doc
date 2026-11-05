package com.tang.missu.api.v1;

//import org.springframework.stereotype.Controller;
import com.tang.missu.dto.PersonDTO;
import com.tang.missu.exception.http.NotFoundException;
import com.tang.missu.sample.ISkill;
//import com.tang.missu.sample.hero.Diana;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

// 注解，给某个类或方法打上标记，就会知道加载它
//@Controller // 视图
//@ResponseBody // 可以提到控制器上，就不用每个方法都写@ResponseBody
@RestController // @RestController 包含了 @Controller和@ResponseBody
//@RequestMapping("/v1/banner") // url都加上前缀
@RequestMapping("/banner")
//@Lazy // 延迟实例化
@Validated
public class BannerController {

    /*注入-setter方式注入*/
    /*private Diana diana;
    @Autowired
    public void setDiana(Diana diana) {
        this.diana = diana;
    }*/

    /* @Autowired注解 - 注入对象，属性注入 */
    @Autowired
    @Qualifier("diana")
//    private Diana diana;
    private ISkill daji;
    // 依赖倒置原则，依赖抽象，所以这里是ISkill这个interface，不要固定使用Diana这个类



    //注入对象 -  构造函数注入
    /*@Autowired
    public BannerController(Diana diana) {
        this.diana = diana;
    }*/

    @GetMapping("/test") /*get请求，url为 /v1/banner/test */
//    @PostMapping post请求
//    @PutMapping
//    @DeleteMapping

    /*@RequestMapping更强大，value和method可以是多个值
    *
    * */
//    @RequestMapping(value = "/test1",method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody /*作用是告诉 Spring：请把这个方法的返回值直接作为 HTTP 响应体（Response Body）写回去，不要走视图解析流程*
    public String test () {
        daji.skillA(); // 这里就可以直接调用diana的方法
//        throw new RuntimeException("错了");
        throw new NotFoundException(10001);
//        return  "hi,ham，你好，十一月";
    }

    @RequestMapping(value = "/test1",method = {RequestMethod.GET,RequestMethod.POST})
    public String test1(){
        return  "test1";
    }

    /* 接收参数
    * @PathVariable - 接收路径中的
    * @RequestParam - 接收 ? 后的 query 参数
    * @RequestBody - 接收body中的参数
    * */
    @GetMapping("/test/params/{id1}")
//    @Validated - 这里@Validated写在方法上无效
    public String testParams(@PathVariable(name = "id1") @Max(10) Integer id, @RequestParam @Length(min = 1,max = 2) String name){
        return "hi,hanber" + id + name;
    }

    //接收body参数
    // @Validated 开启校验
    @PostMapping("/test/post")
    public PersonDTO testPost(@RequestBody @Validated PersonDTO person){
        PersonDTO dto = new PersonDTO(person.getName(),person.getAge(),person.getSchoolDTO());
        return dto;
//          PersonDTO dto = PersonDTO.builder()
//                  .name("ber")
//                  .age(3)
//                  .build();
//          return dto;

    }

}
