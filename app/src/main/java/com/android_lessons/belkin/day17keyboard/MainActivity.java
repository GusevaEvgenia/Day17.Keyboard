package com.android_lessons.belkin.day17keyboard;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener{

    private static long back_pressed;

    final String[] catNamesArray = new String[] { "Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Бобик", "Кристина", "Пушок",
            "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба" };
    private ArrayList<String> catNamesList = new ArrayList<>(Arrays.asList(catNamesArray));

    private ArrayAdapter<String> mAdapter, mWeekOfDayAdapter;

    private String[] mDayOfWeekArray = new String[] { "Понедельник", "Вторник", "Среда",
            "Четверг", "Котопятница", "Субкота", "Воскресенье" };

    private String mMonth, mDayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, catNamesList);
        mWeekOfDayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDayOfWeekArray);

        setListAdapter(mAdapter);

        getListView().setOnItemLongClickListener(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (getListAdapter() == mAdapter) {
            mMonth = (String) l.getItemAtPosition(position);
            setListAdapter(mWeekOfDayAdapter);
            mWeekOfDayAdapter.notifyDataSetChanged();
        } else {
            mDayOfWeek = (String) l.getItemAtPosition(position);
            Toast.makeText(getBaseContext(), mMonth + ": " + mDayOfWeek, Toast.LENGTH_LONG).show();
            setListAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
        //Toast.makeText(getApplicationContext(), "You choose " + l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String selectItem = parent.getItemAtPosition(position).toString();

        mAdapter.remove(selectItem);
        mAdapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), selectItem + "deleted", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        openQuitDialog();

        /*if(back_pressed + 200 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                Toast.makeText(this, "Нажата кнопка Меню", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case KeyEvent.KEYCODE_SEARCH:
                Toast.makeText(this, "Нажата кнопка Поиск", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case KeyEvent.KEYCODE_BACK:
                onBackPressed();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                event.startTracking();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this, "Нажата кнопка громкости", Toast.LENGTH_SHORT)
                        .show();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void openQuitDialog(){
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Таки да!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
