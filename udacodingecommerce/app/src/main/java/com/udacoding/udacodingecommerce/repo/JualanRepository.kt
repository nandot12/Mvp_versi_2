package com.udacoding.udacodingecommerce.repo

import com.udacoding.udacodingecommerce.network.ApiClient
import com.udacoding.udacodingecommerce.ui.keranjang.model.KeranjangResponse
import com.udacoding.udacodingecommerce.ui.listProduk.bottomsheet.model.OrderResponse
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukResponse
import com.udacoding.udacodingecommerce.ui.login.model.LoginResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.ImageResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.JenisProdukResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.KategoriResponse
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JualanRepository {

    private var api = ApiClient.getService()
    private var compositeDisposable = CompositeDisposable()


    fun register(
        name: String, email: String,
        password: String, hp: String,
        responseHandler: (RegisterResponse) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        compositeDisposable.add(
            api.register(name, email, password, hp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    responseHandler(it)
                }, {
                    errorHandler(it)
                })
        )


    }

    fun login(
        email: String, password: String, responseHandler: (LoginResponse) -> Unit,
        handlerError: (Throwable) -> Unit
    ) {

        compositeDisposable.add(api.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                handlerError(it)
            })
        )
    }


    fun kategori(responseHandler: (KategoriResponse) -> Unit, errorHandler: (Throwable) -> Unit) {

        compositeDisposable.add(api.getKategori().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ responseHandler(it) }, { errorHandler(it) })
        )

    }

    fun jenisProduk(
        responseHandler: (JenisProdukResponse) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {

        compositeDisposable.add(api.getJenisProduk().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ responseHandler(it) }, { errorHandler(it) })
        )

    }


    fun getGambar(responseHandler: (ImageResponse) -> Unit, errorHandler: (Throwable) -> Unit) {

        compositeDisposable.add(api.getGambarSlider()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
        )
    }

    fun produkPerKategori(
        idKategori: String, responseHandler: (ProdukResponse) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {

        compositeDisposable.add(api.getProduk(idKategori).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ responseHandler(it) }, { errorHandler(it) })
        )

    }
  fun produkPromo(responseHandler: (ProdukResponse) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {

        compositeDisposable.add(api.promosi().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ responseHandler(it) }, { errorHandler(it) })
        )

    }

    fun order(idUser : String ,total  :String ,harga : String ,idproduk : String ,qty : String ,
               responseHandler: (OrderResponse) -> Unit ,errorHandler : (Throwable) -> Unit){

        compositeDisposable.add(api.order(idUser,total,idproduk,qty,harga)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({responseHandler(it)},{errorHandler(it)}))
    }
 fun addItem(idOrder : String ,total  :String ,harga : String ,idproduk : String ,qty : String ,
               responseHandler: (RegisterResponse) -> Unit ,errorHandler : (Throwable) -> Unit){

        compositeDisposable.add(api.addItem(idOrder,idproduk,qty,harga)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({responseHandler(it)},{errorHandler(it)}))
    }



    fun keranjang(idOrder : String , responseHandler: (KeranjangResponse) -> Unit, errorHandler : (Throwable) -> Unit){

        compositeDisposable.add(api.keranjang(idOrder)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({responseHandler(it)},{errorHandler(it)})
            )
    }


    fun checkout(idOrder : String , total : String , note : String,responseHandler : (RegisterResponse) ->Unit,
                 errorHandler : (Throwable) -> Unit){

        compositeDisposable.add(api.checkout(idOrder,total,note)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({responseHandler(it)},{errorHandler(it)}))
    }







    fun onClear() {

        compositeDisposable.clear()
    }


}