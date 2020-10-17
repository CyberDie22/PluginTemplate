package cyberdie22.template.calculations;

public class BaseDamage {

    public static Integer calculateDamage(Integer damage, Integer strength) { return ((5+damage+(strength/5))*(1+(strength/100))); }
}
