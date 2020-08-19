public class RecipeClass{
    private String[] recipeIngredients ;
    private String[] recipeEquipment ;
    private String[] recipeInstructions;
    private String recipeName;
    private String recipeType;
    private String imageURL;


    public RecipeClass(String recipeName, String recipeType, String[] recipeIngredients, String[] recipeEquipment, String[] recipeInstructions,String imageURL) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeEquipment = recipeEquipment;
        this.recipeInstructions = recipeInstructions;
        this.recipeType = recipeType;
        this.imageURL = imageURL;
    }


    public String getImageURL() {
        return imageURL;
    }

    public String[] getRecipeIngredients() {
        return recipeIngredients;
    }

    public String[] getRecipeEquipment(){
        return recipeEquipment;
    }

    public String[] getRecipeInstructions(){
        return recipeInstructions;
    }

    public String getRecipeName() { return recipeName; }

    public void setRecipeName(String recipeName) { this.recipeName = recipeName; }






    public String getRecipeType(){return recipeType;}

    public String toString() {
        return "\nRecipe Ingredients: " + recipeIngredients + "\nEquipments: " + recipeEquipment + "\nInstructions: " + recipeInstructions + "\n";
    }
}
