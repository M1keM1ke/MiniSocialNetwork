<#import "parts/common.ftl" as c>

<@c.page>

<div class="container">
    <div class="row">
        <div class="col-sm">      </div>
        <div class="col-sm"><h5>температура °C</h5></div>
        <div class="col-sm"><h5>Ощущается
            как °C</h5></div>
        <div class="col-sm"><h5>Вероятность
            осадков %</h5></div>
        <div class="col-sm"><h5>Давление
            мм рт. ст.</h5></div>
        <div class="col-sm"><h5>Скорость
            ветра м/с</h5></div>
        <div class="col-sm"><h5>Влажность
            воздуха</h5></div>
    </div>
    <div class="row">
        <div class="col-sm"><h6>${night}</h6></div>
        <div class="col-sm">${nightAtmosphericPhenomenon}, ${nightWeatherTemperature}</div>
        <div class="col-sm">${nightWeatherFeeling}</div>
        <div class="col-sm">${nightWeatherProbability}</div>
        <div class="col-sm">${nightWeatherPressure}</div>
        <div class="col-sm">${nightWeatherWind}</div>
        <div class="col-sm">${nightWeatherHumidity}</div>
    </div>
    <div class="row">
        <div class="col-sm"><h6>${morning}</h6></div>
        <div class="col-sm">${morningAtmosphericPhenomenon}, ${morningWeatherTemperature}</div>
        <div class="col-sm">${morningWeatherFeeling}</div>
        <div class="col-sm">${morningWeatherProbability}</div>
        <div class="col-sm">${morningWeatherPressure}</div>
        <div class="col-sm">${morningWeatherWind}</div>
        <div class="col-sm">${morningWeatherHumidity}</div>
    </div>
    <div class="row">
        <div class="col-sm"><h6>${day}</h6></div>
        <div class="col-sm">${dayAtmosphericPhenomenon}, ${dayWeatherTemperature}</div>
        <div class="col-sm">${dayWeatherFeeling}</div>
        <div class="col-sm">${dayWeatherProbability}</div>
        <div class="col-sm">${dayWeatherPressure}</div>
        <div class="col-sm">${dayWeatherWind}</div>
        <div class="col-sm">${dayWeatherHumidity}</div>
    </div>
    <div class="row">
        <div class="col-sm"><h6>${evening}</h6></div>
        <div class="col-sm">${eveningAtmosphericPhenomenon}, ${eveningWeatherTemperature}</div>
        <div class="col-sm">${eveningWeatherFeeling}</div>
        <div class="col-sm">${eveningWeatherProbability}</div>
        <div class="col-sm">${eveningWeatherPressure}</div>
        <div class="col-sm">${eveningWeatherWind}</div>
        <div class="col-sm">${eveningWeatherHumidity}</div>
    </div>

</div>
</@c.page>