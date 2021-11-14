package ir.uto.bamboAssisstant.Util;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ir.uto.bamboAssisstant.Fragments.MyRoutineFragment;
import ir.uto.bamboAssisstant.Fragments.ReportFragment;

public class MyFragments {


    public static List<Fragment> getAllFragments(){
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyRoutineFragment());
        fragmentList.add(new ReportFragment());
        return fragmentList;
    }
}
