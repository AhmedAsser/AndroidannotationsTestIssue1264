package any.androidannotationsTestIssue1264;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;

import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity{


	private FragmentTest mFragmentInstance;




	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if(mFragmentInstance == null) mFragmentInstance = FragmentTest_.builder().mType("I am mFragmentInstance").build();
	}

	@CheckedChange(R.id.toggle)
	protected void toggleClick(CompoundButton buttonView, boolean isChecked){
			if(!mFragmentInstance.isVisible()){
				getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragmentInstance).commit();
			} else{
				getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentTest_.builder().mType("Just New Fragment").build()).commit();
			}
	}

}
