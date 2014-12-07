package any.androidannotationsTestIssue1264;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;



@EFragment(R.layout.fragment_main)
public class FragmentTest extends Fragment{

	private int mReplacingNumber=0;
	private int mAfterViewCallingNumber=0;
	private String mMsgContent ="";

	@ViewById(R.id.txt)
	protected TextView mTxt;

	@FragmentArg
	protected String mType;


	@Override
	public void onAttach(Activity activity){
		++mReplacingNumber;
		super.onAttach(activity);
		mMsgContent +="onAttach()"+"\n";
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mMsgContent +="onCreate()"+"\n";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		mMsgContent +="onCreateView()"+"\n";
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		mAfterViewCallingNumber=0;
		mMsgContent +="onViewCreated()"+"\n";
	}

	@AfterViews
	protected void afterViewCreated(){
		++mAfterViewCallingNumber;
		mMsgContent +="@AfterViews()"+getStar(mAfterViewCallingNumber)+"\n";
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		mMsgContent +="onActivityCreated()"+"\n";
	}

	@Override
	public void onResume(){
		super.onResume();
		mMsgContent +="onResume()"+"\n";
		mTxt.setText(mType+" with Re replacing count"+"#"+mReplacingNumber+" ,and @afterViews() called:"+mAfterViewCallingNumber+" time\n\n\n\n"+ mMsgContent);
		mMsgContent ="";
	}


	private String getStar(int n){
		String result="";
		for(int i=0;i<n;i++)
			result+="*";

		return result;
	}
}
