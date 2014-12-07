//Nisreen and Kritika

package com.bucs.virtualmuseumcurator.test;

import android.test.ActivityInstrumentationTestCase2;

import com.bucs.virtualmuseumcurator.museumhome.MuseumDescActivity;
import com.bucs.virtualmuseumcurator.splashpage.EDocentSplashActivity;
import com.robotium.solo.Solo;

public class EDocentSplashActivityTest extends
  ActivityInstrumentationTestCase2<EDocentSplashActivity> {
 
 //private EDocentSplashActivity edocentsplash;
 private Solo solo;

 public EDocentSplashActivityTest() {
  super(EDocentSplashActivity.class);
  // TODO Auto-generated constructor stub
 }

 protected void setUp() throws Exception {
  super.setUp();
   solo = new Solo(getInstrumentation(), getActivity());
   //edocentsplash = getActivity();
 }
 
 public void testSplashPage()  {
  solo.assertCurrentActivity("testing splash", EDocentSplashActivity.class);
  //selects MA
    solo.pressSpinnerItem(0, 0);
    //select Boston
    solo.pressSpinnerItem(1, 0);
    //select MFA
    solo.pressSpinnerItem(2, 0);
    //click on the button
    solo.clickOnButton("Look For A Museum!");
  }
 
 public void testMuseumPage() {
 solo.assertCurrentActivity("testing museums", MuseumDescActivity.class);
 //clicks on the Collections field
 solo.clickInList(4);
 }
 
 public void testCollectionPage() {
  solo.assertCurrentActivity("testing collection page", CollectionPageActivity.class);
  //click on the third art piece in the collection
  solo.clickInList(2);
 }
 
     public void testAudio()
    {
        solo.assertCurrentActivity("testing art page", ArtInfoActivity.class);
        solo.clickOnButton("Play");
        solo.assertCurrentActivity("I just Played the song", ArtInfoActivity.class);
        solo.clickOnButton("Stop");
        solo.assertCurrentActivity("I just Stopped the song", ArtInfoActivity.class);
    }
 

}
