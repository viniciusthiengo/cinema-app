package br.com.thiengo.cinemaapp;

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

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NonSkippableVideoCallbacks;

import br.com.thiengo.cinemaapp.data.Mock;
import br.com.thiengo.cinemaapp.data.SPAdSuporte;
import io.huq.sourcekit.HISourceKit;
import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity implements NonSkippableVideoCallbacks {
    public static final int PERMISSAO_REQUISICAO_CODIGO = 665;

    private MaterialDialog materialDialog;
    private boolean isVideoShown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeCalldorado();
        setContentView(R.layout.activity_main);

        initRecycler();
        initAppOdeal();
        solicitacaoPermissao();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == PERMISSAO_REQUISICAO_CODIGO ){
            if( permissions[0].equalsIgnoreCase( Manifest.permission.ACCESS_FINE_LOCATION )
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED ){

                initHuq();
            }
        }
    }

    private void initializeCalldorado() {
        Calldorado.startCalldorado(this);
    }

    private void solicitacaoPermissao(){

        if ( !ehPermitido( this, Manifest.permission.ACCESS_FINE_LOCATION ) ) {
            boolean mostrarDialog = ActivityCompat
                .shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION );

            if( mostrarDialog ){
                permissaoDialog(
                    getResources().getString(R.string.dialog_permissao_localizacao),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION} );
            }
            else{
                ActivityCompat
                    .requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSAO_REQUISICAO_CODIGO );
            }
        }
        else{
            initHuq();
        }
    }

    public static boolean ehPermitido(Context context, String permissaoCodigo ){
        int permissao = ContextCompat.checkSelfPermission(context, permissaoCodigo);
        return permissao == PackageManager.PERMISSION_GRANTED;
    }

    private void permissaoDialog( String message, final String[] permissions ){
        materialDialog = new MaterialDialog(this)
            .setTitle( R.string.dialog_titulo )
            .setMessage( message )
            .setPositiveButton( R.string.dialog_positive_label, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSAO_REQUISICAO_CODIGO);
                    materialDialog.dismiss();
                }
            })
            .setNegativeButton( R.string.dialog_negative_label, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                }
            });

        materialDialog.show();
    }

    private void initAppOdeal(){
        String appKey = getResources().getString(R.string.app_odeal_api_key);
        Appodeal.setBannerViewId(R.id.appodealBannerView);
        Appodeal.initialize(this, appKey, Appodeal.NON_SKIPPABLE_VIDEO | Appodeal.BANNER | Appodeal.MREC);
        Appodeal.setTesting(true);
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);
        //Appodeal.disableNetwork(this, "cheetah");

        SPAdSuporte.incrementarCounterAbertura(this);
    }

    private void initHuq(){
        Log.i("Log", "Ok HUQ");
        String appKey = getResources().getString(R.string.huq_api_key);
        HISourceKit.getInstance().recordWithAPIKey( appKey, getApplication() );
    }

    private void initRecycler(){
        RecyclerView rvMotos = (RecyclerView) findViewById(R.id.rv_filmes);
        rvMotos.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMotos.setLayoutManager( layoutManager );

        FilmesAdapter adapter = new FilmesAdapter( this, Mock.gerarFilmes() );
        rvMotos.setAdapter( adapter );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER);
    }

    @Override
    public void onBackPressed() {
        if( SPAdSuporte.ehTerceiraAbertura(this)
                && Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)
                && !isVideoShown ){
            Appodeal.show(this, Appodeal.NON_SKIPPABLE_VIDEO);
        }
        super.onBackPressed();

        Log.i("log", "SPAdSupport.ehTerceiraAbertura(this): "+ SPAdSuporte.ehTerceiraAbertura(this));
        Log.i("log", "Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO): "+Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO));
        Log.i("log", "isVideoShown: "+isVideoShown);
    }

    @Override
    public void onNonSkippableVideoLoaded() {
        Log.i("log", "onNonSkippableVideoLoaded()");
    }
    @Override
    public void onNonSkippableVideoFailedToLoad() {}
    @Override
    public void onNonSkippableVideoShown() {
        isVideoShown = true;
    }
    @Override
    public void onNonSkippableVideoFinished() {}
    @Override
    public void onNonSkippableVideoClosed(boolean b) {}
}
