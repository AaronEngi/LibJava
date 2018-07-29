package wang.tyrael.jdkextend;



import java.util.Set;

/**
 * @Auther: wangchao
 * @Date: 2018/7/2 14:35
 * @Description:
 */
public class SetStatic {
    public boolean equal( Set a,  Set b){
        if (a.size() != b.size()) return false;
        for (Object o : a) {
            if (!b.contains(o)) return false;
        }
        return true;
    }
}
