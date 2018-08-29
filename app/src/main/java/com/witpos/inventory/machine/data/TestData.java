package com.witpos.inventory.machine.data;

import com.witpos.inventory.machine.store.bean.Store;
import java.util.ArrayList;
import java.util.List;


public class TestData {


    /**
     * 模拟门面测试数据
     */
    public static List<Store> getTestStoreList() {
        List<Store> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Store store = new Store();
            store.setId(String.valueOf(i));
            store.setName("朝阳大悦城 " + i);
            list.add(store);
        }
        return list;
    }
}
