package com.seweryn.schess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sew on 2015-12-30.
 */
public class Solution  implements java.io.Serializable {
    public Solution(){
      this.boards= new ArrayList<>();
    }
    public List<int[][]> boards;
}
