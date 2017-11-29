package logic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "weapons")
public class WeaponList extends DataList{

    @XmlElement(name = "weapon", type = Weapon.class)
    private ArrayList<Weapon> weapons = new ArrayList<>();

    public WeaponList(){

    }

    public WeaponList(ArrayList<Weapon> weapons){
        this.weapons = weapons;
    }


    public List<Weapon> getWeapons(){
        return weapons;
    }

}
