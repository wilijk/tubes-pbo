package tubes.repository;

import java.util.List;

public interface BaseRepository<T> {
    void simpan(T data) throws Exception;
    List<T> ambilSemua() throws Exception;
} 