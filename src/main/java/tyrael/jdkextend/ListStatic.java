package tyrael.jdkextend;

import java.util.List;

/**
 * @Auther: wangchao
 * @Date: 2018/7/2 14:32
 * @Description:
 */
public class ListStatic {
    public boolean equal(List a,  List b){
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) return false;
        }
        return true;
    }
}
