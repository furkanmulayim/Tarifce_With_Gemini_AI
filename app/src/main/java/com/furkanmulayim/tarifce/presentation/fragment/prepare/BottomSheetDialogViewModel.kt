package com.furkanmulayim.tarifce.presentation.fragment.prepare

import android.app.Application
import com.furkanmulayim.tarifce.presentation.BaseViewModel

class BottomSheetDialogViewModel(application: Application) : BaseViewModel(application) {

    private val prepares =
        "Kırmızı kapya biber, yeşil sivri biber ve soğanları jülyen şekilde doğrayın./Tavuk etini jülyen doğrayıp geniş bir tavada yağsız olarak pişirmeye başlayın./Hafif pişmiş olan tavuklara doğranmış soğan ve biberleri ekleyip soteleme işlemine devam edin. Ardından doğranmış sarımsak ve mantarları tavaya alın./Renk alıp, suyunu çekmeye başlarken tereyağını ilave edin ve bir süre de bu şekilde pişirin./Daha sonra tuz, karabiber ve köri baharatıyla yemeğinizi lezzetlendirin. Son olarak kremayı ilave edin ve yaklaşık 10 dakika orta ateşte ara ara karıştırarak pişirin./Hazır olan köri soslu tavuğu dilediğiniz şekilde sıcak olarak servise hazır hale getirebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz./Servis Önerisi: Köri soslu tavuğu patates kızartması, pilav, salata ve sebze garnitürleriyle servis edebilirsiniz."

    fun preparesList(): List<String> {
        return prepares.split("/")
    }
}