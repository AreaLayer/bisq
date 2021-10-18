/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.core.trade;

import bisq.core.dao.DaoFacade;
import bisq.core.dao.governance.param.Param;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DelayedPayoutAddressProvider {
    public static String getDelayedPayoutAddress(DaoFacade daoFacade) {
        String address = daoFacade.getParamValue(Param.RECIPIENT_BTC_ADDRESS);
        if (isOutdatedAddress(address)) {
            log.warn("Outdated delayed payout address. " +
                    "This can be the case if the DAO is deactivated or if the user has an invalid DAO state." +
                    "We set the address to the recent one (34VLFgtFKAtwTdZ5rengTT2g2zC99sWQLC).");
            return getAddress();
        }
        return address;
    }

    public static boolean isOutdatedAddress(String address) {
        return address.equals("1BVxNn3T12veSK6DgqwU4Hdn7QHcDDRag7") ||   // Initial DAO donation address
                address.equals("3EtUWqsGThPtjwUczw27YCo6EWvQdaPUyp") ||  // burning2019
                address.equals("3A8Zc1XioE2HRzYfbb5P8iemCS72M6vRJV");    // burningman2
    }

    public static String getAddress() {
        return "34VLFgtFKAtwTdZ5rengTT2g2zC99sWQLC";
    }
}
