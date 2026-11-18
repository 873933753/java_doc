package com.tang.missu.optional;

import com.tang.missu.exception.http.NotFoundException;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void testOptional(){
        /* Optional 创建对象 */

        // Optional 构建空对象 - 明确表示"没有值"
        Optional<String> empty = Optional.empty();
        // Optional 构建非空对象 - 值必须非 null，传null报错
        Optional<String> t1 = Optional.of("hi");
        // 可能为 null，用ofNullable
        Optional<String> t2 = Optional.ofNullable(null);

        /* get取值：Optional的精髓，为null取值时就会报错，不会赋给s */
//        String s = t2.get(); // 如果值为null，取值时就报错。如果不用OptIonal 函数栈变深难以溯源

        /*判空，.ifPresent(执行)。也可以isPresent返回true false，但是还要写if else */
        // ifPresent：有值才执行动作
        t2.ifPresent(t -> System.out.println(t));


        /* 为空时给默认值 - optional.orElse("默认值");  */
        // t2为null时，给默认值，否则维持原值
        String s2 = t2.orElse("哈哈");
        System.out.println(s2);

        /* 为空时抛自定义异常 - optional.orElseThrow(自定义异常) */
//        t2.orElseThrow(() -> new NotFoundException(10000));

        Optional<String> t3 =  Optional.ofNullable("ha")
                .map(t -> t+"yaya");
        System.out.println(t3.get()); // 打印hayaya
    }
}
