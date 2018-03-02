package com.model;

import java.util.List;

public interface BlahDAO {
    List<Blah> getBlah(Blah blah);
    void addBlah(Blah blah);
    void deleteBlah(Blah blah);
}
