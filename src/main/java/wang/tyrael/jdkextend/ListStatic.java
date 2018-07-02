package wang.tyrael.jdkextend;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * @Auther: wangchao
 * @Date: 2018/7/2 14:32
 * @Description:
 */
public class ListStatic {
    public boolean equal(@NotNull List a, @NotNull List b){
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) return false;
        }
        return true;
    }
}
