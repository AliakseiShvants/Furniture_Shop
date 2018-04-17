import { Component, OnInit } from '@angular/core';
import {Product} from '../../domain/product/product';
import {Category} from '../../domain/product/category';
import {Manufacturer} from '../../domain/product/manufacturer';
import {Image} from '../../domain/product/image';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css']
})
export class ViewerComponent implements OnInit {

  mockProducts: Product[];
  mockCategory: Category;
  rowCount: number;
  colCount: number;
  constructor() { }

  ngOnInit() {
    this.mockCategory = new Category('chair');
    this.mockProducts = [
      new Product(
        'XXX001',
        'Name1',
        12.00,
        'PRC',
        [
          '../../assets/img/chairs/green.jpg'
        ]
      ),
      new Product(
        'XXX003',
        'Name2',
        7.00,
        'Taiwain',
        [
          '../../assets/img/chairs/red.jpg'
        ]
      ),
      new Product(
        'XXX005',
        'Name3',
        110.00,
        'RF',
        [
          '../../assets/img/chairs/white.jpg'
        ]
      ),
    ];

    this.rowCount = this.mockProducts.length / 3;
    this.colCount = this.mockProducts.length - this.rowCount * 3;
  }

}
