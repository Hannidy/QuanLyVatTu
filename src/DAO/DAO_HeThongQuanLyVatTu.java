package DAO;

import java.util.List;

abstract public class DAO_HeThongQuanLyVatTu<EntityType, KeyType> {
    abstract public void insert(EntityType entity);
    abstract public void update(EntityType entity);
    abstract public void delete(KeyType id); // Trả về boolean để kiểm tra xóa thành công
    abstract public String selectMaxId();
    abstract public List<EntityType> selectById(KeyType keyWord);
    abstract public List<EntityType> selectByKeyWord(KeyType keyWord, KeyType columns);
    abstract public List<EntityType> selectAll();
    abstract protected List<EntityType> selectBySQL (String sql, Object...args);
}

