package Utils;

import java.util.List;

/**
 * Created by Milos on 3/10/2017.
 */
public interface Services<T> {
    List<T> methodGet();
    T methodGetSpecific(int id);
    void methodPost(T t);
    T methodGetNewest();
    boolean methodGetExists(int id);
}
