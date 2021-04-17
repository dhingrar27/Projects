//
//  TableViewCell.swift
//  Bank
//
//  Created by Rabaab Dhingra on 22/02/20.
//  Copyright © 2020 Ayden Panhuyzen. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {
    
    @IBOutlet weak var myLabel: UITextView!
    
    @IBOutlet weak var textview: UITextView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
