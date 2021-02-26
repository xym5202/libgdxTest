package test.archer.Animations;


public enum ArcherMoveEnum {

    MOVEUP("archer/move_up"),
    MOVELEFT("archer/move_left"),
    MOVEDOWN("archer/move_down"),
    MOVERIGHT("archer/move_right"),
    MOVERIGHTUP("archer/move_right_up"),
    MOVERIGHTDOWN("archer/move_right_down"),
    MOVELEFTUP("archer/move_left_up"),
    MOVELEFTDOWN("archer/move_left_down");
    private String name;

    ArcherMoveEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
