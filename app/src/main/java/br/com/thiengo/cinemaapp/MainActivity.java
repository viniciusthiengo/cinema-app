package br.com.thiengo.cinemaapp;

import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.calldorado.Calldorado;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NonSkippableVideoCallbacks;

import br.com.thiengo.cinemaapp.data.Mock;
import br.com.thiengo.cinemaapp.data.SPAdSuporte;
import io.huq.sourcekit.HISourceKit;
import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity
        implements NonSkippableVideoCallbacks, InterstitialCallbacks {

    public static final int PERMISSAO_REQUISICAO_CODIGO = 665;

    private MaterialDialog materialDialog;
    private boolean videoApresentado = false;
    private boolean intersApresentado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeCalldorado();
        setContentView(R.layout.activity_main);

        initRecycler();
        initAppOdeal();
        //solicitacaoPermissao();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER);
        verificacaoPermissaoHuq();
    }

    @Override
    public void onBackPressed() {
        if( SPAdSuporte.ehTerceiraAbertura(this) ){

            if( Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)
                    && !videoApresentado ){
                Appodeal.show(this, Appodeal.NON_SKIPPABLE_VIDEO);
            }
            else if( Appodeal.isLoaded(Appodeal.INTERSTITIAL)
                    && !videoApresentado
                    && !intersApresentado){
                Appodeal.show(this, Appodeal.INTERSTITIAL);
            }
        }
        super.onBackPressed();
    }

    private void initRecycler(){
        RecyclerView rvFilmes = (RecyclerView) findViewById(R.id.rv_filmes);
        rvFilmes.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvFilmes.setLayoutManager( layoutManager );

        FilmesAdapter adapter = new FilmesAdapter( this, Mock.gerarFilmes() );
        rvFilmes.setAdapter( adapter );
    }

    private void initializeCalldorado() {
        Calldorado.startCalldorado(this);
    }

    private void verificacaoPermissaoHuq(){
        if ( ehPermitido( this, Manifest.permission.ACCESS_FINE_LOCATION ) ) {
            initHuq();
        }
    }

    public static boolean ehPermitido(Context context, String permissaoCodigo ){
        int permissao = ContextCompat.checkSelfPermission(context, permissaoCodigo);
        return permissao == PackageManager.PERMISSION_GRANTED;
    }

    private void initAppOdeal(){
        String key = getResources().getString(R.string.app_odeal_api_key);
        Appodeal.setBannerViewId(R.id.appodealBannerView);
        Appodeal.initialize(this, key, Appodeal.BANNER | Appodeal.NON_SKIPPABLE_VIDEO | Appodeal.INTERSTITIAL);
        /*
        * COLOQUE A LINHA A SEGUIR COMO FALSE CASO ESTEJA
        * INDO ASSINAR O APLICATIVO PARA ENVIA-LO A PLAY
        * STORE.
        * */
        Appodeal.setTesting(true);
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);

        /*
        * SOMENTE DESCOMENTE A LINHA A SEGUIR CASO VOCÊ TENHA
        * PROBLEMAS COM O TOAST DE "API CHEETAH NÃO ENCONTRADA".
        * */
        //Appodeal.disableNetwork(this, "cheetah");

        SPAdSuporte.incrementarCounterAbertura(this);
    }

    private void initHuq(){
        Log.i("Log", "initHuq() called");
        String appKey = getResources().getString(R.string.huq_api_key);
        HISourceKit.getInstance().recordWithAPIKey( appKey, getApplication() );
    }


    @Override
    public void onNonSkippableVideoLoaded() {
        Log.i("log", "onNonSkippableVideoLoaded()");
    }
    @Override
    public void onNonSkippableVideoFailedToLoad() {}
    @Override
    public void onNonSkippableVideoShown() {
        videoApresentado = true;
    }
    @Override
    public void onNonSkippableVideoFinished() {}
    @Override
    public void onNonSkippableVideoClosed(boolean b) {}


    @Override
    public void onInterstitialLoaded(boolean b) {}
    @Override
    public void onInterstitialFailedToLoad() {}
    @Override
    public void onInterstitialShown() { intersApresentado = true; }
    @Override
    public void onInterstitialClicked() {}
    @Override
    public void onInterstitialClosed() {}
}
