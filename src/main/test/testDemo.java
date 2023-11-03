import com.hh.reggie.common.enums.OrderStatusEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class testDemo {

    @Test
    public void test(){
        String a = "a";
        String b = "b";
        String c = "c";
        System.out.println(c.hashCode());
        c = a + b;
        System.out.println(c.hashCode());

        StringBuffer sb = new StringBuffer("sb");
        System.out.println(sb.hashCode());
        sb.append("ccc");
        System.out.println(sb.hashCode());
    }
}
