import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BngInfoService } from 'app/entities/bng-info/bng-info.service';
import { IBngInfo, BngInfo } from 'app/shared/model/bng-info.model';

describe('Service Tests', () => {
  describe('BngInfo Service', () => {
    let injector: TestBed;
    let service: BngInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IBngInfo;
    let expectedResult: IBngInfo | IBngInfo[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BngInfoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BngInfo(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BngInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new BngInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BngInfo', () => {
        const returnedFromService = Object.assign(
          {
            endSz: 'BBBBBB',
            lineId: 'BBBBBB',
            portName: 'BBBBBB',
            tsLastQuery: 'BBBBBB',
            tsLastQuerySuccess: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BngInfo', () => {
        const returnedFromService = Object.assign(
          {
            endSz: 'BBBBBB',
            lineId: 'BBBBBB',
            portName: 'BBBBBB',
            tsLastQuery: 'BBBBBB',
            tsLastQuerySuccess: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BngInfo', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
