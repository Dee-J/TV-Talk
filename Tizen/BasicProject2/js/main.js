


/**
 * @name TVtuner module manager
 * @type {Object}
 * @property {Number} channelListenerID - The identifier of the listener for channel changes
 * @property {HTMLElement} progInfo - HTML element which displays information at the top of the screen
 * @property {Object} tvTunerObj - wrapper for tizen.tvchannel namespace
 */
var TVtuner = (function() {

    var channelListenerID = null,
        progInfo = null,
        tvTunerObj = tizen.tvchannel;

    /**
     * Displays logging information on the screen and in the console.
     * @param {string} msg - Message to log.
     */
    function log(msg) {
        var logsEl = document.getElementById('logs');

        if (msg) {
            // Update logs
            console.log('[TVChannel]: ', msg);
            logsEl.innerHTML += msg + '<br />';
        } else {
            // Clear logs
            logsEl.innerHTML = '';
        }

        logsEl.scrollTop = logsEl.scrollHeight;
    }

    /**
     * @description Tune callback for tune(), tuneUp() and tuneDown() functions when tuning has been completed
     * @type {Object}
     * @private
     */
    tuneCB = {
        /**
         * @description Method invoked when the tuning is successfully done
         * @method tuneCB.onsuccess()
         * @param channel - The tuned channel information
         * @param type - The window type
         */
        onsuccess: function(channel, type) {
            log("tune is successfully done. And there is a signal.");
            // Display channel and program info on onsuccess callback
            displayTest();
        },
        /**
         * @description Method invoked when the tuning is successfully done, but there is no signal on the switched channel
         * @method tuneCB.onnosignal()
         */
        onnosignal: function() {
            log("tune is successfully done. But there is no signal.");
        },

        /**
         * @description Method invoked when information about the current program is available after the tune operation
         * @method tuneCB.onprograminforeceived()
         * @param program - The information about program on offer from the tuned channel
         * @param type - The window type
         */
        onprograminforeceived: function(program, type) {
            log("Program is ready");
        }
    }

    /**
     * @description Method invoked when the channel has been changed.
     * @method TVtuner.channelChangeCB()
     * @param channelInfo {Object} The switched channel information
     * @param type - The window type
     * @private
     */
    function channelChangeCB (channelInfo, type) {
        log("Switched to the new channel (major = " + channelInfo.major + ", minor = " + channelInfo.minor + ", channel name = " + channelInfo.channelName);
    };

    /**
     * @description The method invoked when the list of TV program informations is retrieved.
     * @method TVtuner.programListCB()
     * @param programInfos {Object} - A list of TV program informations of a channel
     * @private
     */
    function programListCB (programInfos) {
        log("getting all available programs is successfully retrieved.");
        log("retrieved " + programInfos.length + " programs");
        for (var i = 0; i < programInfos.length; i++) {
            log("--------------- Program " + i + " ---------------");
            log("title = " + programInfos[i].title);
            log("duration = " + programInfos[i].duration);
            // you can get other attributes in the retrieved ProgramInfo objects.
        }
    };

    /**
     * @description Method invoked when all channels are found.
     * @method TVtuner.channelListCB()
     * @param channels {Object} - A list of channel Informations
     * @private
     */
    function channelListCB (channels) {
        log("getChannelList() is successfully done. Totally " + channels.length + " channels are retrieved.");
        for (var i = 0; i < channels.length; i++) {
            log("----- Channel [" + i + "] -----");

            // The major channel number. The first number in a two-part number used to identify a virtual channel.
            // Each virtual channel carries one service, such as a television program.
            log("Major channel = " + channels[i].major);

            // The minor channel number. The second number in a two-part number used to identify a virtual channel.
            // The minor number changes for each different service that is or will be present in a DTV transport stream.
            log("Minor channel = " + channels[i].minor);

            // Channel Name to represent the station's indentity
            log("Channel Name = " + channels[i].channelName);

            // Program number
            log("Program Number = " + channels[i].programNumber);

            // PTC(Physical Transmission Channel) number
            log("PTC = " + channels[i].ptc);

            // The logical channel number. It is used in DVB(Digital Video Broadcasting) standards for digital television.
            log("LCN = " + channels[i].lcn);

            // It is a number that uniquely identifies a source of scheduled programming.
            log("Source ID = " + channels[i].sourceID);

            // TSID (Transport Stream ID or transmission signal ID)
            log("Transport stream ID = " + channels[i].transportStreamID);

            // original network ID
            log("Original network ID = " + channels[i].originalNetworkID);

            // service name
            log("Service name = " + channels[i].serviceName);
        }
    };

    /**
     * @description initialize TVtuner module
     * @method TVtuner.init()
     * @public
     */
    function init() {
        log("init() called");
        if (window.tizen === undefined) {
            log('This application needs to be run on Tizen device');
            return;
        }
        displayVersion();

        progInfo = document.getElementById("progInfo").getElementsByTagName("h1")[0];

        keyHandler();
        addChannelChangeListener();
        displayTest();
    }

    /**
     * @desciption Register keys used in the application
     * @method TVtuner.keyHandler()
     * @private
     */
    function keyHandler() {

        // registering keys
        try {
            tizen.tvinputdevice.getSupportedKeys()
                .forEach(function (k){
                    if ([
                        'ChannelUp',
                        'ChannelDown',
                        'Guide',
                        'Info'
                    ].indexOf(k.name) > -1) {
                        tizen.tvinputdevice.registerKey(k.name);
                        log('Subscribed key:' + k.name + ' - ' + k.code);
                    }
                });
        } catch (e) {
            console.error('Could not subscribe keys, exception occurred:', e);
        }

        // key manager based on windon event listener
        window.addEventListener("keydown", function(keyEvent) {
            log("keyEvent: " + keyEvent.keyCode);
            switch(keyEvent.keyCode) {
                case 10009:
                    // Return key
                    tizen.application.getCurrentApplication().exit();
                    break;
                case 427:
                    // Channel Up key
                    tuneUp();
                    break;
                case 428:
                    // Channel Down key
                    tuneDown();
                    break;
                case 458:
                    // Guide key
                    getProgramList();
                    break;
                case 457:
                    // Info key
                    getProgramInfo();
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Display application version
     */
    function displayVersion() {
        var el = document.createElement('div');
        el.id = 'version';
        el.innerHTML = 'ver: ' + tizen.application.getAppInfo().version;
        document.body.appendChild(el);
    }

    /**
     * @description Adds a channel change listener for getting notified about the channel changes.
     * @method TVtuner.addChannelChangeListener()
     * @public
     */
    function addChannelChangeListener() {
        try {
            channelListenerID = tvTunerObj.addChannelChangeListener(channelChangeCB);
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    /**
     * @description Test function to display current information about channel and program at the top of the screen
     * @method TVtuner.displayTest()
     * @public
     */
    function displayTest() {
        var channel = getCurrentChannel();
        var program = getProgramInfo();

        progInfo.innerHTML = channel.major + " " + channel.channelName + ": " + program.title;
    }

    /**
     * @description Gets information about the current channel.
     * @method TVtuner.getCurrentChannel()
     * @returns {ChannelInfo} - the current channel information
     * @public
     */
    function getCurrentChannel() {
        try {
            var channel = tvTunerObj.getCurrentChannel();
            log("The current channel name is "  + channel.channelName);
            log("The current channel's major number is "  + channel.major);
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }

        return channel;
    };

    /**
     * @description Gets information about the current television program.
     * @method TVtuner.getProgramInfo()
     * @returns {ProgramInfo} - the information of television program
     * @public
     */
    function getProgramInfo() {
        var program;
        try {
            program = tvTunerObj.getCurrentProgram();
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }

        if(program == null) {
            program = {
                title: "No title available"
            }
            return program;
        }

        if(program.title.length == 0) {
            program.title = "no title available";
        }

        (program) ? log("The current program is titled: "  + program.title) : log("There is no program");

        return program;
    };

    /**
     * @description Removes the listener to stop receiving notifications for the channel changes.
     * @method TVtuner.removeChannelChangeListener()
     * @public
     */
    function removeChannelChangeListener() {
        try {
            tvTunerObj.removeChannelChangeListener(channelListenerID);
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    /**
     * @description Gets the TV channel list.
     * @method TVtuner.getChannelList()
     * @param numb {Number} - number of channels to retreive
     * @public
     */
    function getChannelList(numb) {

        var amount = 10;

        if(numb && typeof(numb) == "number") {
            amount = numb;
        }

        try {
            // channelListCB - The method to invoke when a list of tv channels is retrieved successfully.
            // null - optional error callback
            // "ALL" - The channel navigation mode. By default, this attribute is set to ALL.
            // 0 - The starting index. By default, this attribute is set to 0.
            // amount - gets 10 channel information among all channels if no numb is typed
            tvTunerObj.getChannelList(channelListCB, null, "ALL", 0, amount);
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    /**
     * @description Gets a list of programs for a specific channel within a specified time duration. If this method is
     * invoked without the duration parameter, all available program informations are retrieved.
     * @method TVtuner.getProgramList()
     * @public
     */
    function getProgramList() {
        try {
            var channel = tvTunerObj.getCurrentChannel();
            // channel - The channel
            // tizen.time.getCurrentDateTime() - The starting time to search programs.
            // programListCB - The method to invoke when a list of programs is retrieved successfully.
            tvTunerObj.getProgramList(channel, tizen.time.getCurrentDateTime(), programListCB);
        } catch (error) {
            log(error.name);
        }
    };

    /**
     * @description Tunes the specified channel.
     * @method TVtuner.tune()
     * @param index {Number} - Tune to the typed number of channel
     * @public
     */
    function tune(index) {

        function channelCB(channels) {
            log("getting channels is successful. " + channels.length + " channels are retreived.");
            if (channels.length === 0 ) {
                log("There is no found channel.");
                return;
            }

            if (index >=  channels.length) {
                log("channel number is beyond the available list");
                return;
            }

            try {
                //{major: channels[index].major} - The tuning option. By default, this attribute is set to null.
                // tuneCB - The method to invoke when the tunning operation is completed successfully.
                tvTunerObj.tune({major: channels[index].major}, tuneCB);
            } catch(error) {
                log("Error name = "+ error.name + ", Error message = " + error.message);
            }
        }

        function errorCB(error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }

        try {
            // requests to get information about 10 channels.
            tvTunerObj.getChannelList(channelCB, errorCB, "ALL", 0, 10);
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    /**
     * @description Changes channel up.
     * @method TVtuner.tuneUp()
     * @public
     */
    function tuneUp() {
        try {
            // tuneCB - The method to invoke when the tunning operation is completed successfully.
            tvTunerObj.tuneUp(tuneCB);
        } catch(e) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    /**
     * @description Changes channel down.
     * @method TVtuner.tuneDown()
     * @public
     */
    function tuneDown() {
        try {
            // tuneCB - The method to invoke when the tunning operation is completed successfully.
            tvTunerObj.tuneDown(tuneCB);
        } catch(e) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    /**
     * @description Retrieves information about all available channels.
     * @method TVtuner.findChannel()
     * @param major The major channel number
     * @param minor The minor channel number
     * @public
     */
    function findChannel(major, minor) {

        //The method to invoke when the channel searching is done successfully.
        function successCB(channels) {
            log("findChannel() is successfully done. Totally " + channels.length + " channels are found.");
            for (var i = 0; i < channels.length; i++) {
                log("----- Channel [" + i + "] -----");
                log("Major channel = " + channels[i].major);
                log("Minor channel = " + channels[i].minor);
                log("Channel Name = " + channels[i].channelName);
                log("Program Number = " + channels[i].programNumber);
                // you can get other attributes provided in ChannelInfo
            }
        }

        try {
            tvTunerObj.findChannel(major, minor, successCB);
        } catch (error) {
            log("Error name = "+ error.name + ", Error message = " + error.message);
        }
    };

    return {
        init: init,
        addChannelChangeListener: addChannelChangeListener,
        getCurrentChannel: getCurrentChannel,
        removeChannelChangeListener: removeChannelChangeListener,
        getChannelList: getChannelList,
        getProgramList: getProgramList,
        tune: tune,
        tuneUp: tuneUp,
        tuneDown: tuneDown,
        findChannel: findChannel
    }
}());

window.onload = TVtuner.init;