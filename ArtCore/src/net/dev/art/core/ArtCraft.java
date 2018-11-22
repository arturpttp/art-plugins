package net.dev.art.core;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class ArtCraft {

	public class CraftNormal
	{

		private ShapelessRecipe recipe;

		private ItemStack result;

		public CraftNormal( ItemStack result ){

			setResult( result );
			setRecipe( new ShapelessRecipe( result ) );
		}

		public void add( Material ingredient , int data ) {

			recipe.addIngredient( ingredient , data );
		}

		public void remove( Material ingredient , int data ) {

			recipe.removeIngredient( ingredient , data );
		}

		public ShapelessRecipe getRecipe() {

			return recipe;
		}

		public void setRecipe( ShapelessRecipe recipe ) {

			this.recipe = recipe;
		}

		public ItemStack getResult() {

			return result;
		}

		public void setResult( ItemStack result ) {

			this.result = result;
		}

	}

	public class CraftExtra
	{

		private ShapedRecipe recipe;

		private ItemStack result;

		private Material[] items = new Material[9];

		private int[] datas = new int[9];

		public CraftExtra( ItemStack result ){

			setResult( result );
			setRecipe( new ShapedRecipe( result ) );
			for ( int x = 0; x < datas.length; x++ ) {
				datas[x] = 0;
			}
		}

		public void set( int slot , Material material ) {

			set( slot , material , 0 );

		}

		public void set( int slot , Material material , int data ) {

			if ( ( slot < 1 ) || ( slot > 9 ) ) { return; }
			items[slot - 1] = material;
			datas[slot - 1] = data;

		}

		public ShapedRecipe getRecipe() {

			try {
				recipe.shape( ( items[0] == null ? " " : "A" ) + ( items[1] == null ? " " : "B" )
					+ ( items[2] == null ? " " : "C" ) , ( items[3] == null ? " " : "D" )
					+ ( items[4] == null ? " " : "E" ) + ( items[5] == null ? " " : "F" ) ,
					( items[6] == null ? " " : "G" ) + ( items[7] == null ? " " : "H" )
						+ ( items[8] == null ? " " : "I" ) );

				char shape = 'A';
				for ( int x = 0; x < items.length; x++ ) {
					if ( items[x] == null ) {
						shape++;
						continue;
					}
					recipe.setIngredient( shape , items[x] , datas[x] );
					shape++;
				}

			} catch ( Exception e ) {
				e.printStackTrace();
			}

			return recipe;
		}

		public void setRecipe( ShapedRecipe recipe ) {

			this.recipe = recipe;
		}

		public ItemStack getResult() {

			return result;
		}

		public void setResult( ItemStack result ) {

			this.result = result;
		}
	}
	
}
