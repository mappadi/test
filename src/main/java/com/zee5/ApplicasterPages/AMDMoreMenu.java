package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Bindu

public class AMDMoreMenu {

	public static By objMoreMenu = By.xpath("//*[@id='bb_bottom_bar_icon']");
	public static By objProfile = By.xpath("//*[@text='P']");
	public static By objUserType = By.xpath("//*[@text='Guest']");
	public static By objBackbutton = By.xpath("//*[@text='a']");
	public static By objBuySubscription = By.xpath("//*[@text='Buy Subscription']");
	public static By objMySubscription = By.xpath("//*[@text='My Subscription']");
	public static By objMyTransactions = By.xpath("//*[@text='My Transactions']");
	public static By objWatchlist = By.xpath("//*[@text='Watchlist']");
	public static By objMyRemainders = By.xpath("//*[@text='My Reminders']");
	public static By objHaveaPrepaidCode = By.xpath("//*[@text='Have a Prepaid Code?']"); 
	public static By objSettings = By.xpath("//*[@text='Settings']");
	public static By objHomeIcon = By.xpath("//*[@text='Home']");
	public static By objUpcomingIcon = By.xpath("//*[@text='Upcoming' and @id='bb_bottom_bar_title']");
	public static By objDownloadsIcon = By.xpath("//*[@text='Downloads']");
	public static By objMoreMenuIcon = By.xpath("//*[@text='More']");
	public static By objLogout = By.xpath("//*[@text='Logout']");
	public static By objLogoutBtn = By.xpath("//*[@id='logoutButton']");

	public static By objInviteAFriend = By.xpath("//*[@text='Invite a Friend']");
	public static By objAboutUs = By.xpath("//*[@text='About Us']");
	public static By objTermsOfUse = By.xpath("//*[@id='termsofuse']");
	public static By objPrivacyPolicy = By.xpath("//*[@id='privacypolicy']");
	public static By objBuildVersion = By.xpath("//*[@id='version']");
	public static By objDescription = By.xpath("//*[@text='Please connect to the internet and try again.']");

	public static By objParentalControl = By.xpath("//*[@id='parentalControl']");
	public static By objPasswordField = By.xpath("//*[@id='txtET_password_input']");
	public static By objPasswordContinueBtn = By.xpath("//*[@id='btn_verify_email_continue']");

	public static By objRestrictAllContent = By.xpath("//*[@text='Restrict All Content']");
	public static By objContinueBtn = By.xpath("//*[@id='btn_parental_control_continue']");

	public static By objSetPin = By.xpath("//*[@id='txt_verify_email_account_header']");
	public static By objParentalLockPin1 = By.xpath("//*[@id='otpEditText1']");
	public static By objParentalLockPin2 = By.xpath("//*[@id='otpEditText2']");
	public static By objParentalLockPin3 = By.xpath("//*[@id='otpEditText3']");
	public static By objParentalLockPin4 = By.xpath("//*[@id='otpEditText4']");
	public static By objParentalLockDone = By.xpath("//*[@id='dialog_done']");

	public static By objDownloadIcon = By.xpath("//*[@id='downloadTv']");
	public static By objRelatedSearchResult = By
			.xpath("(//*[@id='searchResultsContent']//following-sibling::*[@id='item_primary_text'])[1]");
	public static By objDataSaver = By.xpath("//*[@text='Data saver']");
	public static By objStartDownload = By.xpath("//*[@id='bottomSheetDialogStartDownloadBtn']");
	public static By objDownloadedIcon = By.xpath("(//*[@id='downloadView'])[2]");
	public static By objPlayerBackBtn = By.xpath("//*[@id='icon_down']");
	public static By objEnterPinCTA = By.xpath("//*[@text='Enter PIN']");

	// ----- Sub-Menu under settings
	// --------video streaming sub menu---------//
	public static By objvideoQualityOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/videoQuality']");
	public static By objVideoStreamOverWifiOnlyOption = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/videoOverWifi']");
	public static By objVideoAutoPlay = By.xpath("//*[@resource-id='com.graymatrix.did:id/autoPlayLabel']");

	public static By objVideo_Quality(String QualityOption) {
		return By.xpath("//*[@id='qualityPixels' and @text='" + QualityOption + "']");
	}

	public static By objVideo_WifiOnly = By.xpath("//*[@id='videoOverWifiSwitch']");
	public static By objVideo_Autoply = By.xpath("//*[@id='autoPlaySwitch']");

	// ------------Video quality screen---------//
	public static By objVideoQualityScreenTitle = By
			.xpath("//*[@id='selector_screen_title' and @text='Select Video Quality']");
	public static By objAutoOption = By.xpath("//*[@text='Auto']");
	public static By objCloseButtonInVideoQualityScreen = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/selector_selection']");

	public static By objSelectedVideoQualityOption(String qualityOption) {
		return By.xpath("//*[@text='" + qualityOption
				+ "']/preceding-sibling::*[@resource-id='com.graymatrix.did:id/selectionImageSelector']");
	}

	// ------------Downloads Sub menu-----------//
	public static By objDownloads_Quality = By.xpath("//*[@id='downloadLabel']");
	public static By objDownloads_WifiOnly = By.xpath("//*[@id='downloadOverWifiSwitch']");

	public static By objContentLang = By.xpath("//*[@id='contentLanguageValue']");
	public static By objDisplayLang = By.xpath("//*[@id='displayLanguageValue']");

	public static By objClearSearchHistory = By.xpath("//*[@id='searchHistoryAction']");
	public static By objResetDefault = By.xpath("//*[@id='resetSettingAction']");

	public static By objSetPinContinueBtn = By.xpath("//*[@id='btn_set_pin_continue']");
	public static By objNoRestriction = By.xpath("//*[@text='No Restriction']");
	public static By objDownloadDoneIcon = By.xpath("//*[@id='img_state']");

	public static By objHelpCentre = By.xpath("//*[@text='Help Centre'] | //*[@text='Help Center']");

	// developed by Sushma
	public static By objBackButtonInSettingsScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_back']");
	public static By objSettingsScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Settings']");
	public static By objVideoStreamingMenuTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/videoStreamingTitle']");
	public static By objDownloadsMenuTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/downloadTitle']");
	public static By objLanguageMenuTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/languageTitle']");
	public static By objSearchHistroyLabel = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchHistoryLabel']");
	public static By objResetSettingsToDefault = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/resetSettingLabel']");
	// About Us Screen
	public static By objAboutUsHeader = By.xpath("//*[@content-desc='About us' or @text='About us']");

	// Logout

	public static By objLogoutPopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_logout_header']");
	public static By objCancelButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/logoutCancelButton']");
	public static By objLogoutButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/logoutButton']");
	public static By objProfileHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/header']");

	public static By objcloseButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_exit']");
	public static By objAboutUsDescription = By.xpath("//*[contains(@content-desc,'At Zee5')] | //*[contains(@text,'At Zee5')]");
	// Terms of Use Screen
	public static By objYesBtnResetDefault = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_exit_yes']");

	public static By objPrivacyPolicyPageWithinbrowser = By
			.xpath("//*[@class='android.view.View' and ./*[@text='Privacy Policy']]");
//	public static By objPrivacyPolicyHeader = By.xpath("//*[@text='Privacy Policy']");
	public static By objSettingsInKannada = By.xpath("//*[@text='ಸೆಟ್ಟಿಂಗ್ಗಳು']");
	public static By objAboutUsInKannada = By.xpath("//*[@text='ನಮ್ಮ ಬಗ್ಗೆ']");
	public static By objHelpCenterInKannada = By.xpath("//*[@text='ಸಹಾಯ ಕೇಂದ್ರ']");
	public static By objTermsInKannada = By.xpath("//*[@resource-id='com.graymatrix.did:id/termsofuse']");
	public static By objPrivacyPolicyInKannada = By.xpath("//*[@resource-id='com.graymatrix.did:id/privacypolicy']");
	public static By objBackbtnInSettings = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_back']");
	public static By objTextInAboutUsScreen = By.xpath("//*[@text='Zee5 can be accessed at ']");
	// Help Center
	public static By objSearchBarInHelpCenter = By.xpath("//*[@class='android.widget.EditText']");
	
	public static By objPrepaidCodePopUp = By.xpath("//*[@id='txt_prepaid_code']");
	
	public static By objDisplayLanguageOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/displayLanguage']");
	public static By objContentLanguageOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/contentLanguage']");

	public static By objSearchResult(String title) {
		return By.xpath("//*[@id='item_primary_text' and contains(text(), '"+title+"')]");
	}
	
	public static By objWhatsAppMessage = By.xpath("//*[@id='content' and ./*[@text='WhatsApp']]");
	
	public static By objTransactionDate1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_invoicedate'])[1]");
	public static By objTransactionPackName1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_txt_allaccesspack'])[1]");
	public static By objTransactionPackDuration1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_duration'])[1]");
	public static By objTransactionPackRental1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_amount'])[1]");
	public static By objTransactionPackPaymentMode1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_paymentmode'])[1]");
	public static By objTransactionPackCountry1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_countryvalue'])[1]");
	public static By objTransactionPackAutoRenewal1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_renewl_type'])[1]");
	public static By objTransactionPackStatus1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/mytran_status_value'])[1]");
	public static By objDownloadInvoice1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/btn_dialog_done'])[1]");
	
	public static By objSubscribeNowCTA = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_subscribe_now']");
	public static By objSubNowCTA = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_sub_now']");
	public static By objMyTransactionsHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and contains(text(),'My Transactions')]");
	public static By objLoginRegisterText = By.xpath("//*[@resource-id='com.graymatrix.did:id/sub_header']");
	public static By objUserName = By.xpath("//*[@resource-id='com.graymatrix.did:id/header']");
	
	public static By objTransactionPackDuration = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_duration']");
	public static By objTransactionPackRental = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_amount']");
	public static By objTransactionPackPaymentMode = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_paymentmode']");
	public static By objTransactionPackCountry = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_countryvalue']");
	public static By objTransactionPackAutoRenewal = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_renewl_type']");
	public static By objTransactionPackStatus = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_status_value']");
	public static By objDownloadInvoice = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_dialog_done']");
	public static By objTransactionDate = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_invoicedate']");
	public static By objTransactionPackName = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_txt_allaccesspack']");
	public static By objSubscriptionPackDuration = By.xpath("//*[@resource-id='com.graymatrix.did:id/pack_duration']");
	public static By objSubscriptionPackPrice = By.xpath("//*[@resource-id='com.graymatrix.did:id/pack_amount']");
	public static By objSubscriptionPackExpiryDate = By.xpath("//*[@resource-id='com.graymatrix.did:id/renewal_date']");
	public static By objSubscriptionPackCountry = By.xpath("//*[@resource-id='com.graymatrix.did:id/pack_country']");
	public static By objSubscriptionPackPaymentMode = By.xpath("//*[@resource-id='com.graymatrix.did:id/payment_mode']");
	public static By objSubscriptionPackOfferings = By.xpath("//*[@resource-id='com.graymatrix.did:id/benefits']");
	public static By objsubscriptionPackDotBelowTheCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/dot_v3']");
	public static By objMySubscriptionPack = By.xpath("(//*[@class='android.widget.LinearLayout'])[4]");
	public static By objSubscriptionPackCancelRenewal = By.xpath("//*[@resource-id='com.graymatrix.did:id/cancel_renewal']");
	public static By objBrowseAllPacks = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_browse_packs']");
	public static By objsubscriptionPackDotBelowTheSecondCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/dot_v1']");
	public static By objNoActivePlans = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_nosubscription_title']");
	public static By objMySubscriptionsHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and contains(text(),'My Subscriptions')]");
	public static By objsubscriptionPackStatus = By.xpath("//*[@resource-id='com.graymatrix.did:id/status']");

	public static By objDot2InShareOptions = By.xpath("(//*[@id='tw_resolver_page_navi']/*[@class='android.widget.ImageView'])[2]");
	public static By objFacebook = By.xpath("//*[@id='content' and ./*[@text='News Feed']]");
	public static By objShareLink = By.xpath("//*[@contentDescription='Shared Link: Watch TV Serials, Original Shows, Movies, News & Live TV Online, 100,000+ hours of TV Shows from ZEE network, Movies, International & Original content, music");
	
	public static By objCompleteProfilePopUp = By.xpath("//*[@id='tellUsMoreContainer']");
	public static By objShareIconOnPlayer = By.xpath("//*[@id='icon_share']");
	public static By objShareNowFb = By.xpath("//*[@contentDescription='SHARE NOW']");
	public static By objWatchCreditsCTA = By.xpath("//*[@text='Watch Credits']");
	public static By objUpNext = By.xpath("//*[@text='Up Next']");
	public static By objTitleInLandscape(String title) {
		return By.xpath("//*[@id='title_main' and contains(text(), '"+title+"')]");
	}
	public static By objHyperLinkInAboutUsScreen = By
			.xpath("//*[@class='android.view.View']//*[@contentDescription='www.zee5.com'] | //*[@text='www.zee5.com']");
	public static By objPageNotFoundMsg = By.xpath("//*[contains(@text,'404! Not Found')] | //*[contains(@content-desc,'404! Not Found')]");
	public static By objNetworkerrormsg = By.xpath("//*[@resource-id='android:id/message']");
	public static By objTermsOfUseHeader = By.xpath("//*[@text='Terms of Use'] | //*[@content-desc='Terms of Use']");
	public static By objTermsDescription = By.xpath("//*[contains(@content-desc,'Welcome to ZEE5')] | //*[contains(@text,'Welcome to ZEE5')]");
	public static By objsupportHyperlinkInTermsOfUse = By.xpath("//*[@text='support.in@zee5.com'] | //*[@content-desc='support.in@zee5.com']");
	public static By objfeedbackLinkInTermsOfUse = By.xpath("//*[@content-desc='feedback.yono@sbi.co.in']");
	public static By objzee5HyperlinkinTermsOfUse = By.xpath("//*[@content-desc='www.ZEE5.com'] |//*[@text='www.zee5.com']");
	public static By objsubscribelinkInTermsofUse = By
			.xpath("//*[@content-desc='https://www.zee5.com/myaccount/subscription']");
	public static By objInternetErrormsg = By.xpath("//*[@content-desc='You are not connected to internet'] | //*[@text='You are not connected to internet']");
	
	public static By objPrivacyDescription = By.xpath("//*[contains(@content-desc,'We')] | //*[contains(@text,'We')]");
	public static By objHyperlinkInPrivacyPolicy = By.xpath("(//*[@content-desc='Privacy Policy' or @text='Privacy Policy'])[2]");
	public static By objHelpCenterHeader = By.xpath("//*[@content-desc='Help Center'] | //*[@text='Help Center']");
	
	public static By objQueriesHeader(String text) {
		return By.xpath("(//*[contains(@contentDescription,'" + text + "')] | //*[contains(text(),'"+text+"')])");
	}

	public static By objArticleTitle(String title) {
		return By.xpath("(//*[contains(@content-desc,'"+title+"')] | //*[contains(@text,'"+title+"')])[2]");
	}
	
	public static By objGettingStartedHeader = By.xpath("//*[@content-desc='Getting Started ' or @text='Getting Started ']");
	public static By objMyAccountHeader = By.xpath("//*[@content-desc='My Account ' or @text='My Account ']");
	public static By objsupportlinkInprivacypolicy = By.xpath("//*[@content-desc='support@zee5.com.']");
	public static By objPrivacyPolicyHeader = By.xpath("(//*[contains(@content-desc,'Privacy Policy')] | //*[contains(@text,'Privacy Policy')])[1]");
	
	public static By objshareOptions = By.xpath("//*[@resource-id='android:id/tw_resolver_pagemode_page_list' or @id='resolver_slide']");

	public static By objShareOptions = By.xpath("//*[@id='text1']");
	public static By objShareOptions(int index ) {
			return By.xpath("(//*[@id='text1'])["+index+"]");
		}

	public static By objDotsInShareOptions = By.xpath("//*[@id='tw_resolver_page_navi']/*[@class='android.widget.ImageView']");
	public static By objDotsInShareOptions(int index) {
		return By.xpath("(//*[@id='tw_resolver_page_navi']/*[@class='android.widget.ImageView'])["+index+"]");
	}
}
