package ldurazo.github.pokeapi.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities = new ArrayList<Ability>();
    @SerializedName("attack")
    @Expose
    private Integer attack;
    @SerializedName("catch_rate")
    @Expose
    private Integer catchRate;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("defense")
    @Expose
    private Integer defense;
    @SerializedName("descriptions")
    @Expose
    private List<Description> descriptions = new ArrayList<Description>();
    @SerializedName("egg_cycles")
    @Expose
    private Integer eggCycles;
    @SerializedName("egg_groups")
    @Expose
    private List<EggGroup> eggGroups = new ArrayList<EggGroup>();
    @SerializedName("ev_yield")
    @Expose
    private String evYield;
    @SerializedName("evolutions")
    @Expose
    private List<Evolution> evolutions = new ArrayList<Evolution>();
    @SerializedName("exp")
    @Expose
    private Integer exp;
    @SerializedName("growth_rate")
    @Expose
    private String growthRate;
    @SerializedName("happiness")
    @Expose
    private Integer happiness;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("hp")
    @Expose
    private Integer hp;
    @SerializedName("male_female_ratio")
    @Expose
    private String maleFemaleRatio;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("moves")
    @Expose
    private List<Move> moves = new ArrayList<Move>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("national_id")
    @Expose
    private Integer nationalId;
    @SerializedName("pkdx_id")
    @Expose
    private Integer pkdxId;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("sp_atk")
    @Expose
    private Integer spAtk;
    @SerializedName("sp_def")
    @Expose
    private Integer spDef;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("speed")
    @Expose
    private Integer speed;
    @SerializedName("sprites")
    @Expose
    private List<SpriteUri> spriteUris = new ArrayList<SpriteUri>();
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("types")
    @Expose
    private List<Type> types = new ArrayList<Type>();
    @SerializedName("weight")
    @Expose
    private String weight;

    /**
     *
     * @return
     * The abilities
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     *
     * @param abilities
     * The abilities
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    /**
     *
     * @return
     * The attack
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     *
     * @param attack
     * The attack
     */
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    /**
     *
     * @return
     * The catchRate
     */
    public Integer getCatchRate() {
        return catchRate;
    }

    /**
     *
     * @param catchRate
     * The catch_rate
     */
    public void setCatchRate(Integer catchRate) {
        this.catchRate = catchRate;
    }

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     *
     * @param defense
     * The defense
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     *
     * @return
     * The descriptions
     */
    public List<Description> getDescriptions() {
        return descriptions;
    }

    /**
     *
     * @param descriptions
     * The descriptions
     */
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     *
     * @return
     * The eggCycles
     */
    public Integer getEggCycles() {
        return eggCycles;
    }

    /**
     *
     * @param eggCycles
     * The egg_cycles
     */
    public void setEggCycles(Integer eggCycles) {
        this.eggCycles = eggCycles;
    }

    /**
     *
     * @return
     * The eggGroups
     */
    public List<EggGroup> getEggGroups() {
        return eggGroups;
    }

    /**
     *
     * @param eggGroups
     * The egg_groups
     */
    public void setEggGroups(List<EggGroup> eggGroups) {
        this.eggGroups = eggGroups;
    }

    /**
     *
     * @return
     * The evYield
     */
    public String getEvYield() {
        return evYield;
    }

    /**
     *
     * @param evYield
     * The ev_yield
     */
    public void setEvYield(String evYield) {
        this.evYield = evYield;
    }

    /**
     *
     * @return
     * The evolutions
     */
    public List<Evolution> getEvolutions() {
        return evolutions;
    }

    /**
     *
     * @param evolutions
     * The evolutions
     */
    public void setEvolutions(List<Evolution> evolutions) {
        this.evolutions = evolutions;
    }

    /**
     *
     * @return
     * The exp
     */
    public Integer getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     * The exp
     */
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    /**
     *
     * @return
     * The growthRate
     */
    public String getGrowthRate() {
        return growthRate;
    }

    /**
     *
     * @param growthRate
     * The growth_rate
     */
    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }

    /**
     *
     * @return
     * The happiness
     */
    public Integer getHappiness() {
        return happiness;
    }

    /**
     *
     * @param happiness
     * The happiness
     */
    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The hp
     */
    public Integer getHp() {
        return hp;
    }

    /**
     *
     * @param hp
     * The hp
     */
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    /**
     *
     * @return
     * The maleFemaleRatio
     */
    public String getMaleFemaleRatio() {
        return maleFemaleRatio;
    }

    /**
     *
     * @param maleFemaleRatio
     * The male_female_ratio
     */
    public void setMaleFemaleRatio(String maleFemaleRatio) {
        this.maleFemaleRatio = maleFemaleRatio;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return
     * The moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     *
     * @param moves
     * The moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The nationalId
     */
    public Integer getNationalId() {
        return nationalId;
    }

    /**
     *
     * @param nationalId
     * The national_id
     */
    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    /**
     *
     * @return
     * The pkdxId
     */
    public Integer getPkdxId() {
        return pkdxId;
    }

    /**
     *
     * @param pkdxId
     * The pkdx_id
     */
    public void setPkdxId(Integer pkdxId) {
        this.pkdxId = pkdxId;
    }

    /**
     *
     * @return
     * The resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     *
     * @param resourceUri
     * The resource_uri
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    /**
     *
     * @return
     * The spAtk
     */
    public Integer getSpAtk() {
        return spAtk;
    }

    /**
     *
     * @param spAtk
     * The sp_atk
     */
    public void setSpAtk(Integer spAtk) {
        this.spAtk = spAtk;
    }

    /**
     *
     * @return
     * The spDef
     */
    public Integer getSpDef() {
        return spDef;
    }

    /**
     *
     * @param spDef
     * The sp_def
     */
    public void setSpDef(Integer spDef) {
        this.spDef = spDef;
    }

    /**
     *
     * @return
     * The species
     */
    public String getSpecies() {
        return species;
    }

    /**
     *
     * @param species
     * The species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     *
     * @return
     * The speed
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The spriteUris
     */
    public List<SpriteUri> getSpriteUris() {
        return spriteUris;
    }

    /**
     *
     * @param spriteUris
     * The spriteUris
     */
    public void setSpriteUris(List<SpriteUri> spriteUris) {
        this.spriteUris = spriteUris;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    /**
     *
     * @return
     * The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

}