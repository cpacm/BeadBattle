package net.cpacm.beadbattle;

/**
 * @author: cpacm
 * @date: 2016/11/23
 * @desciption:
 */

public class BeadEntity {

    public final static int BEAD_RED = 0;
    public final static int BEAD_GREEN = 1;
    public final static int BEAD_YELLOW = 2;
    public final static int BEAD_BLUE = 3;
    public final static int BEAD_PURPLE = 4;

    //珠子的类型
    private int beadType;

    //珠子的id
    private int beadId;

    public BeadEntity(int beadType, int beadId) {
        this.beadType = beadType;
        this.beadId = beadId;
    }
}
