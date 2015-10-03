package br.com.ciandt.helio.worldwonders.infrastructure;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import br.com.ciandt.helio.worldwonders.database.provider.WorldWondersContentProvider;
import br.com.ciandt.helio.worldwonders.database.table.WondersTable;

public class ProviderTest {

	private Context mContext;
	private ContentResolver mResolver;
	
	public ProviderTest(Context ctx) {
		mContext = ctx;
		mResolver = mContext.getContentResolver();
	}
	
	public void insertTestWonder(String name, String country, String description, String imageUrl) {
		ContentValues values = new ContentValues();
		values.put(WondersTable.NAME, name);
		values.put(WondersTable.COUNTRY, country);
		values.put(WondersTable.DESCRIPTION, description);
		values.put(WondersTable.IMAGE_URL, imageUrl);
		
		long id = ContentUris.parseId(mResolver.insert(WorldWondersContentProvider.WONDER_CONTENT_URI, values));
		Toast.makeText(mContext, "Maravilha inserida! Id = "+id, Toast.LENGTH_LONG).show();
	}
	
	public void deleteTestWonder(int id) {
		int count = mResolver.delete(WorldWondersContentProvider.WONDER_CONTENT_URI, WondersTable.ID + "=?", new String[]{Integer.toString(id)});
		if (count == 0) {
			Toast.makeText(mContext, "Não foi encontrada nenhuma entrada com o id "+id+". Deleção falhou.", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(mContext, "Maravilha com id = "+id+" deletada com sucesso!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void deleteTestWonder(String name) {
		String WHERE = WondersTable.NAME + "=?";
		String[] WHERE_ARGS = new String[] {name};
		int count = mResolver.delete(WorldWondersContentProvider.WONDER_CONTENT_URI, WHERE, WHERE_ARGS);
		if (count == 0) {
			Toast.makeText(mContext, "Não foi encontrada nenhuma entrada com o nome "+name+". Deleção falhou.", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(mContext, "Maravilha com name = "+name+" deletada com sucesso!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void printWonderList() {
		Cursor c = mResolver.query(WorldWondersContentProvider.WONDER_CONTENT_URI, null, null, null, null);
		StringBuilder names = new StringBuilder();
		names.append("[ ");
		if (c != null) {
			while (c.moveToNext()) {
				String wonderName = c.getString(c.getColumnIndex(WondersTable.NAME));
				names.append(wonderName);
				if (!c.isLast()) {
					names.append(", ");
				}
			}
		}
		names.append(" ]");
		
		Toast.makeText(mContext, "Maravilhas no db: "+names, Toast.LENGTH_LONG).show();
	}
}
