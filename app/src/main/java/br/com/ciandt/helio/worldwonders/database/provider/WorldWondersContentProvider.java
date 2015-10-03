package br.com.ciandt.helio.worldwonders.database.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import br.com.ciandt.helio.worldwonders.database.Database;
import br.com.ciandt.helio.worldwonders.database.table.WondersTable;

public class WorldWondersContentProvider extends ContentProvider {

    // TASK: escolha uma AUTHORITY para seu ContentProvider
    // Dica: E uma boa pratica que este nome seja baseado no seu package name (pode ser apenas o package name)
    private static final String AUTHORITY = "br.com.ciandt.helio.worldwonders";
    private static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    private static UriMatcher URI_MATCHER;
    private Database database;

    // References of DIR and ID
    private static final int WONDER_DIR = 0;
    private static final int WONDER_ID = 1;

    // Configuration of UriMatcher
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(AUTHORITY, WonderContent.CONTENT_PATH, WONDER_DIR);
        URI_MATCHER.addURI(AUTHORITY, WonderContent.CONTENT_PATH + "/#", WONDER_ID);
    }

    // Content URIs
    public static final Uri WONDER_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, WonderContent.CONTENT_PATH);

    // Content Information of Entities

    /**
     * Provides the content information of the PlaceTable.
     */
    private static final class WonderContent implements BaseColumns {

        /**
         * Specifies the content path of the PostTable for the required Uri.
         */
        public static final String CONTENT_PATH = "wonder";

        /**
         * Sepecifies the type for the folder and the single item of the PlaceTable.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mdsdacp.wonder";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.mdsdacp.wonder";
    }

    /**
     * Instantiate the database, when the content provider is created.
     *
     * @return true
     */
    @Override
    public final boolean onCreate() {
        // TASK: instanciar o database (guardar na varaiavel database).
        // DICA: para obter o contexto, utilize getContext
        database = new Database(getContext());

        return true;
    }

    @Override
    public final String getType(Uri uri) {

        switch (URI_MATCHER.match(uri)) {

            case WONDER_DIR:
                return WonderContent.CONTENT_TYPE;
            case WONDER_ID:
                return WonderContent.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public final Cursor query(Uri uri, String[] projection, String selection,
                              String[] selectionArgs, String sortOrder) {

        // TASK: obtenha uma instancia do SQLiteDatabase a partir do database
        // Lembre-se voce esta no metodo query. Portanto, voce precisara LER do banco

        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

        // Construa um objeto SQLiteQueryBuilder para facilitar a query
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        switch (URI_MATCHER.match(uri)) {

            case WONDER_ID:
                // Caso queira obter apenas uma linha da tabela, podera passar o _id da wonder
                // desejada na propria Uri (sem necessidade de uma clausula WHERE)
//                queryBuilder.appendWhere(WondersTable.ID + "=" + uri.getPathSegments().get(1));

            case WONDER_DIR:
                // TASK: setar a tabela "wonders" no query builder
                sqLiteQueryBuilder.setTables(WondersTable.TABLE_NAME);
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Cursor cursor = null;
        // TASK: executar a query passando os parametros necessarios e atribuir o retorno ao cursor acima
        // DICA: utilize o metodo query do SQLiteQueryBuilder

        cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    /**
     * Insert given values to given uri.
     *
     * @param uri
     * @param values from type ContentValues
     * @return uri of inserted element from type Uri
     */
    @Override
    public final Uri insert(Uri uri, ContentValues values) {

        // TASK: Obter uma instancia do SQLiteDatabase a partir do database

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        // DICA: Lembre-se que vc esta no metodo insert, portanto voce
        // ira precisar ESCREVER no db

        try {
            // Abrir a conexao com o DB (beginTransaction)
            sqLiteDatabase.beginTransaction();
            // DICA: utilize a instancia do SQLiteDatabase obtida no passo acima

            switch (URI_MATCHER.match(uri)) {

                case WONDER_DIR:
                case WONDER_ID:
                    // TASK: utilizar os metodos do SQLitedatabase para inserir a entrada no BD
                    // DICA: utilize insertOrThrow

                    long id = sqLiteDatabase.insertOrThrow(WondersTable.TABLE_NAME, null, values);

                    // a insercao retorna o ID da entrada inserida. Guarde e use-o para
                    // Montar a Uri de retorno: ela deve ser formada pela Uri da tabela de wonders
                    // + id da linha recem inserida.
                    // DICA: utilize o metodo ContentUris.withAppendedId para fazer o append do id
                    uri = ContentUris.withAppendedId(uri, id);

                    // Notifique os observers do DB da nova Uri inserida (boa pratica):
                    // DICA: Utilize o metodo notifyChange do ContentResolver

                    getContext().getContentResolver().notifyChange(uri, null);

                    // Notificar o SQLiteDatabase que a transacao foi bem sucedida:
                    // DICA: chamar metodo setTransactionSuccessful
                    // Retornar a Uri inserida
                    sqLiteDatabase.setTransactionSuccessful();
                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        } finally {
            // TASK: Finalizar a conexcao (a transaction)
            sqLiteDatabase.endTransaction();
            return uri;
        }
    }

    /**
     * Update given values of given uri, returning number of affected rows.
     *
     * @param uri
     * @param values        from type ContentValues
     * @param selection
     * @param selectionArgs
     * @return number of affected rows
     */
    @Override
    public final int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase dbConnection = database.getWritableDatabase();
        int updateCount = 0;

        try {

            dbConnection.beginTransaction();

            switch (URI_MATCHER.match(uri)) {

                case WONDER_DIR:
                    updateCount = dbConnection.update(WondersTable.TABLE_NAME, values, selection, selectionArgs);
                    dbConnection.setTransactionSuccessful();
                    break;
                case WONDER_ID:
                    Long postId = ContentUris.parseId(uri);
                    updateCount = dbConnection.update(WondersTable.TABLE_NAME, values,
                            WondersTable.ID + "=" + postId + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")"),
                            selectionArgs);
                    dbConnection.setTransactionSuccessful();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        } finally {
            dbConnection.endTransaction();
        }

        if (updateCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updateCount;
    }

    /**
     * Delete given elements by their uri and return the number of deleted rows.
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return number of deleted rows
     */
    @Override
    public final int delete(Uri uri, String selection, String[] selectionArgs) {

        int quantidadeLinhasDeletadas;
        // TASK: Obter uma instancia do SQLiteDatabase a partir do database
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        // DICA: Lembre-se que vc esta no metodo delete, portanto voce
        // ira precisar ESCREVER no db

        try {

            // Abrir a conexao com o DB (beginTransaction)
            sqLiteDatabase.beginTransaction();
            // DICA: utilize a instancia do SQLiteDatabase obtida no passo acima

            switch (URI_MATCHER.match(uri)) {

                case WONDER_DIR:
                    // TASK: utilizar os metodos do SQLitedatabase para deletar a entrada no BD
                    // DICA: guarde o retorno (numero de linhas deletadas para retornar este valor)
                    quantidadeLinhasDeletadas = sqLiteDatabase.delete(WondersTable.TABLE_NAME, selection, selectionArgs);
                    // TASK: Notificar o SQLiteDatabase que a transacao foi bem sucedida
                    sqLiteDatabase.setTransactionSuccessful();

                    break;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }

        } finally {
            // TASK: Finalizar a conexao (a transaction)
            sqLiteDatabase.endTransaction();
        }

        // TASK: Caso o numero de delecoes seja maior que 0
        // notificar observers (como foi feito no insert)
        if (quantidadeLinhasDeletadas > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        // TASK: Retorne o numero de linhas deletas
        return quantidadeLinhasDeletadas;
    }

    /**
     * Insert a batch given contValues to given uri.
     *
     * @param uri
     * @param contValues
     * @return number of inserted rows
     */
    @Override
    public final int bulkInsert(Uri uri, ContentValues[] contValues) {

        SQLiteDatabase dbConnection = database.getWritableDatabase();
        String table = null;
        int insertCount = 0;

        try {

            dbConnection.beginTransaction();

            int matchUri = URI_MATCHER.match(uri);

            // delete table
            switch (matchUri) {

                case WONDER_DIR:
                case WONDER_ID:
                    table = WondersTable.TABLE_NAME;
                    dbConnection.delete(table, null, null);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }

            // insert the batch values
            for (ContentValues contValue : contValues) {
                dbConnection.insert(table, null, contValue);
                insertCount++;
            }

            // notify the UI when necessary
            switch (matchUri) {

                case WONDER_DIR:
                case WONDER_ID:
                    dbConnection.setTransactionSuccessful();
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        } finally {
            dbConnection.endTransaction();
        }

        return insertCount;
    }
}